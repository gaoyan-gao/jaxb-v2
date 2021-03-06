/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.tools.xjc.model;

import java.util.Collection;

import javax.activation.MimeType;
import javax.xml.namespace.QName;

import com.sun.codemodel.JClass;
import com.sun.codemodel.JExpression;
import com.sun.tools.xjc.model.nav.NClass;
import com.sun.tools.xjc.model.nav.NType;
import com.sun.tools.xjc.outline.Aspect;
import com.sun.tools.xjc.outline.Outline;
import com.sun.xml.bind.v2.model.annotation.Locatable;
import com.sun.xml.bind.v2.model.core.EnumLeafInfo;
import com.sun.xml.bind.v2.model.core.ID;
import com.sun.xml.bind.v2.model.core.NonElement;
import com.sun.xml.bind.v2.model.core.Element;
import com.sun.xml.bind.v2.runtime.Location;
import com.sun.xml.xsom.XSComponent;
import com.sun.xml.xsom.XmlString;

import org.xml.sax.Locator;

/**
 * Transducer that converts a string into an "enumeration class."
 * 
 * The structure of the generated class needs to precisely
 * follow the JAXB spec.
 * 
 * @author Kohsuke KAWAGUCHI
 */
public final class CEnumLeafInfo implements EnumLeafInfo<NType,NClass>, NClass, CNonElement
{
    /**
     * The {@link Model} object to which this bean belongs.
     */
    public final Model model;

    /**
     * The parent into which the enum class should be generated.
     */
    public final CClassInfoParent parent;

    /**
     * Short name of the generated type-safe enum.
     */
    public final String shortName;

    private final QName typeName;

    private final XSComponent source;

    /**
     * Represents the underlying type of this enumeration
     * and its conversion.
     *
     * <p>
     * To parse XML into a constant, we use the base type
     * to do {@code lexical -> value}, then use a map to pick up the right one.
     *
     * <p>
     * Hence this also represents the type of the Java value.
     * For example, if this is an enumeration of xs:int,
     * then this field will be Java int.
     */
    public final CNonElement base;


    /**
     * List of enum members.
     */
    public final Collection<CEnumConstant> members;

    private final CCustomizations customizations;

    /**
     * @see #getLocator()
     */
    private final Locator sourceLocator;

    public String javadoc;

    public CEnumLeafInfo(Model model,
                         QName typeName,
                         CClassInfoParent container,
                         String shortName,
                         CNonElement base,
                         Collection<CEnumConstant> _members,
                         XSComponent source,
                         CCustomizations customizations,
                         Locator _sourceLocator) {
        this.model = model;
        this.parent = container;
        this.shortName = model.allocator.assignClassName(parent,shortName);
        this.base = base;
        this.members = _members;
        this.source = source;
        if(customizations==null)
            customizations = CCustomizations.EMPTY;
        this.customizations = customizations;
        this.sourceLocator = _sourceLocator;
        this.typeName = typeName;

        for( CEnumConstant mem : members )
            mem.setParent(this);

        model.add(this);

        // TODO: can we take advantage of the fact that enum can be XmlRootElement?
    }

    /**
     * Source line information that points to the place
     * where this type-safe enum is defined.
     * Used to report error messages.
     */
    public Locator getLocator() {
        return sourceLocator;
    }

    public QName getTypeName() {
        return typeName;
    }

    public NType getType() {
        return this;
    }

    /**
     * @deprecated
     *      why are you calling the method whose return value is known?
     */
    public boolean canBeReferencedByIDREF() {
        return false;
    }

    public boolean isElement() {
        return false;
    }

    public QName getElementName() {
        return null;
    }

    public Element<NType,NClass> asElement() {
        return null;
    }

    public NClass getClazz() {
        return this;
    }

    public XSComponent getSchemaComponent() {
        return source;
    }

    public JClass toType(Outline o, Aspect aspect) {
        return o.getEnum(this).clazz;
    }

    public boolean isAbstract() {
        return false;
    }

    public boolean isBoxedType() {
        return false;
    }

    public String fullName() {
        return parent.fullName()+'.'+shortName;
    }

    public boolean isPrimitive() {
        return false;
    }

    public boolean isSimpleType() {
        return true;
    }


    /**
     * The spec says the value field in the enum class will be generated
     * only under certain circumstances.
     *
     * @return
     *      true if the generated enum class should have the value field.
     */
    public boolean needsValueField() {
        for (CEnumConstant cec : members) {
            if(!cec.getName().equals(cec.getLexicalValue()))
                return true;
        }
        return false;
    }

    public JExpression createConstant(Outline outline, XmlString literal) {
        // correctly identifying which constant it maps to is hard, so
        // here I'm cheating
        JClass type = toType(outline,Aspect.EXPOSED);
        for (CEnumConstant mem : members) {
            if(mem.getLexicalValue().equals(literal.value))
                return type.staticRef(mem.getName());
        }
        return null;
    }

    @Deprecated
    public boolean isCollection() {
        return false;
    }

    @Deprecated
    public CAdapter getAdapterUse() {
        return null;
    }

    @Deprecated
    public CNonElement getInfo() {
        return this;
    }

    public ID idUse() {
        return ID.NONE;
    }

    public MimeType getExpectedMimeType() {
        return null;
    }

    public Collection<CEnumConstant> getConstants() {
        return members;
    }

    public NonElement<NType,NClass> getBaseType() {
        return base;
    }

    public CCustomizations getCustomizations() {
        return customizations;
    }

    public Locatable getUpstream() {
        throw new UnsupportedOperationException();
    }

    public Location getLocation() {
        throw new UnsupportedOperationException();
    }
}
