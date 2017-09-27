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

/* this file is generated by RelaxNGCC */
package com.sun.tools.jxc.gen.config;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.Attributes;
import com.sun.tools.jxc.NGCCRuntimeEx;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.XMLReader;

import java.util.List;
    import java.util.ArrayList;
    import java.io.File;

/**
 * <p><b>
 *     Auto-generated, do not edit.
 * </b></p>
 */
public class Config extends NGCCHandler {
    private String bd;
    private Schema _schema;
    protected final NGCCRuntimeEx $runtime;
    private int $_ngcc_current_state;
    protected String $uri;
    protected String $localName;
    protected String $qname;

    public final NGCCRuntime getRuntime() {
        return($runtime);
    }

    public Config(NGCCHandler parent, NGCCEventSource source, NGCCRuntimeEx runtime, int cookie) {
        super(source, parent, cookie);
        $runtime = runtime;
        $_ngcc_current_state = 8;
    }

    public Config(NGCCRuntimeEx runtime) {
        this(null, runtime, runtime, -1);
    }

    private void action0()throws SAXException {
        this.schema.add (_schema);
}

    private void action1()throws SAXException {
        baseDir = $runtime.getBaseDir(bd);
}

    public void enterElement(String $__uri, String $__local, String $__qname, Attributes $attrs) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 0:
            {
                revertToParentFromEnterElement(this, super._cookie, $__uri, $__local, $__qname, $attrs);
            }
            break;
        case 1:
            {
                if(($__uri.equals("") && $__local.equals("schema"))) {
                    NGCCHandler h = new Schema(this, super._source, $runtime, 19, baseDir);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 8:
            {
                if(($__uri.equals("") && $__local.equals("config"))) {
                    $runtime.onEnterElementConsumed($__uri, $__local, $__qname, $attrs);
                    $_ngcc_current_state = 7;
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 4:
            {
                if(($__uri.equals("") && $__local.equals("classes"))) {
                    NGCCHandler h = new Classes(this, super._source, $runtime, 22);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        case 2:
            {
                if(($__uri.equals("") && $__local.equals("schema"))) {
                    NGCCHandler h = new Schema(this, super._source, $runtime, 20, baseDir);
                    spawnChildFromEnterElement(h, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    $_ngcc_current_state = 1;
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
            }
            break;
        case 7:
            {
                if(($ai = $runtime.getAttributeIndex("","baseDir"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendEnterElement(super._cookie, $__uri, $__local, $__qname, $attrs);
                }
                else {
                    unexpectedEnterElement($__qname);
                }
            }
            break;
        default:
            {
                unexpectedEnterElement($__qname);
            }
            break;
        }
    }

    public void leaveElement(String $__uri, String $__local, String $__qname) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 0:
            {
                revertToParentFromLeaveElement(this, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 1:
            {
                if(($__uri.equals("") && $__local.equals("config"))) {
                    $runtime.onLeaveElementConsumed($__uri, $__local, $__qname);
                    $_ngcc_current_state = 0;
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 7:
            {
                if(($ai = $runtime.getAttributeIndex("","baseDir"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendLeaveElement(super._cookie, $__uri, $__local, $__qname);
                }
                else {
                    unexpectedLeaveElement($__qname);
                }
            }
            break;
        default:
            {
                unexpectedLeaveElement($__qname);
            }
            break;
        }
    }

    public void enterAttribute(String $__uri, String $__local, String $__qname) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 0:
            {
                revertToParentFromEnterAttribute(this, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendEnterAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 7:
            {
                if(($__uri.equals("") && $__local.equals("baseDir"))) {
                    $_ngcc_current_state = 6;
                }
                else {
                    unexpectedEnterAttribute($__qname);
                }
            }
            break;
        default:
            {
                unexpectedEnterAttribute($__qname);
            }
            break;
        }
    }

    public void leaveAttribute(String $__uri, String $__local, String $__qname) throws SAXException {
        int $ai;
        $uri = $__uri;
        $localName = $__local;
        $qname = $__qname;
        switch($_ngcc_current_state) {
        case 0:
            {
                revertToParentFromLeaveAttribute(this, super._cookie, $__uri, $__local, $__qname);
            }
            break;
        case 5:
            {
                if(($__uri.equals("") && $__local.equals("baseDir"))) {
                    $_ngcc_current_state = 4;
                }
                else {
                    unexpectedLeaveAttribute($__qname);
                }
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendLeaveAttribute(super._cookie, $__uri, $__local, $__qname);
            }
            break;
        default:
            {
                unexpectedLeaveAttribute($__qname);
            }
            break;
        }
    }

    public void text(String $value) throws SAXException {
        int $ai;
        switch($_ngcc_current_state) {
        case 0:
            {
                revertToParentFromText(this, super._cookie, $value);
            }
            break;
        case 6:
            {
                bd = $value;
                $_ngcc_current_state = 5;
                action1();
            }
            break;
        case 2:
            {
                $_ngcc_current_state = 1;
                $runtime.sendText(super._cookie, $value);
            }
            break;
        case 7:
            {
                if(($ai = $runtime.getAttributeIndex("","baseDir"))>=0) {
                    $runtime.consumeAttribute($ai);
                    $runtime.sendText(super._cookie, $value);
                }
            }
            break;
        }
    }

    public void onChildCompleted(Object result, int cookie, boolean needAttCheck)throws SAXException {
        switch(cookie) {
        case 19:
            {
                this._schema = ((Schema)result);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        case 22:
            {
                this.classes = ((Classes)result);
                $_ngcc_current_state = 2;
            }
            break;
        case 20:
            {
                this._schema = ((Schema)result);
                action0();
                $_ngcc_current_state = 1;
            }
            break;
        }
    }

    public boolean accepted() {
        return(($_ngcc_current_state == 0));
    }

    
   private File baseDir;
   private Classes classes;
   private List schema = new ArrayList();
   public Classes getClasses() { return this.classes;}
   public File getBaseDir() { return baseDir;}
   public List getSchema() { return this.schema;}

}

