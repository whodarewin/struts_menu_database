/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 2003 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Velocity", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */

package net.sf.navigator.displayer;

import org.apache.velocity.context.InternalContextAdapter;
import org.apache.velocity.runtime.parser.node.Node;
import org.apache.velocity.runtime.parser.node.SimpleNode;
import org.apache.velocity.runtime.directive.Directive;
import org.apache.velocity.runtime.parser.ParserTreeConstants;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.exception.ParseErrorException;

import org.apache.velocity.runtime.parser.node.ASTReference;

import java.io.Writer;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;


/**
 *   Simple directive to provide ability to trap a local value for
 *   iteration.
 *
 *   Ex :
 *
 *   #set($foo = 1)
 *   $foo
 *   #local($foo)
 *      #set($foo = 2)
 *      $foo
 *   #end
 *    $foo
 *
 *    should output
 *
 *    1
 *       2
 *    1
 *
 * @author <a href="mailto:geirm@optonline.net">Geir Magnusson Jr.</a>
 * @version $Id: LocalDirective.java,v 1.1 2003/10/31 05:59:06 mraible Exp $ 
 */
public class LocalDirective extends Directive
{
    public String getName()
    {
        return "local";
    }

        public int getType()
        {
            return BLOCK;
        }

        public boolean render(InternalContextAdapter context,
                               Writer writer, Node node)
        throws IOException,  MethodInvocationException, ResourceNotFoundException,
                ParseErrorException
        {
            Map data = new HashMap();

            int num = node.jjtGetNumChildren();

            /*
             * get the references
             */

            for (int i=0; i < num; i++)
            {
                SimpleNode child = (SimpleNode) node.jjtGetChild(i);

                /*
                 * if a block, just execute
                 */

                if (child.getType() == ParserTreeConstants.JJTBLOCK)
                {
                    child.render(context, writer);
                    break;
                }
                else
                {
                    /* save the values - for now, just w/ ref to test */

                    if (child.getType() == ParserTreeConstants.JJTREFERENCE)
                    {
                        data.put(child, child.execute(null, context));
                    }
                    else
                    {
                        System.out.println("unhandled type");
                    }
                }
            }

            Iterator it = data.keySet().iterator();

            while(it.hasNext())
            {
                ASTReference ref = (ASTReference) it.next();

                ref.setValue(context, data.get(ref));
            }

            return true;
        }
}
