/* 
 *  Copyright (C) 2000 - 2010 TagServlet Ltd
 *
 *  This file is part of Open BlueDragon (OpenBD) CFML Server Engine.
 *  
 *  OpenBD is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  Free Software Foundation,version 3.
 *  
 *  OpenBD is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with OpenBD.  If not, see http://www.gnu.org/licenses/
 *  
 *  Additional permission under GNU GPL version 3 section 7
 *  
 *  If you modify this Program, or any covered work, by linking or combining 
 *  it with any of the JARS listed in the README.txt (or a modified version of 
 *  (that library), containing parts covered by the terms of that JAR, the 
 *  licensors of this Program grant you additional permission to convey the 
 *  resulting work. 
 *  README.txt @ http://www.openbluedragon.org/license/README.txt
 *  
 *  http://www.openbluedragon.org/
 */

package cfml.parsing.cfscript;

import java.util.Vector;

import org.antlr.v4.runtime.Token;

import cfml.parsing.reporting.ParseException;

public class CFFunctionExpression extends CFMember {
	private static final long serialVersionUID = 1L;
	
	private CFIdentifier nameId;
	private Vector<CFExpression> args; // Vector of CFExpression's
	private boolean isUDF = true;
	
	// private boolean isParamExists;
	public CFFunctionExpression(CFIdentifier _name, Vector<CFExpression> _args) throws ParseException {
		this(_name.getToken(), _name, _args);
	}
	
	public CFFunctionExpression(Token t, CFIdentifier _name, Vector<CFExpression> _args) throws ParseException {
		super(t, null);
		nameId = _name;
		args = _args;
		isUDF = false;
	}
	
	public byte getType() {
		return CFExpression.FUNCTION;
	}
	
	public String getFunctionName() {
		if (nameId instanceof CFFullVarExpression) {
			return ((CFFullVarExpression) nameId).getLastIdentifier().Decompile(0).toLowerCase();
		}
		return nameId == null ? "" : nameId.Decompile(0).toLowerCase();
	}
	
	public boolean isUDF() {
		return isUDF;
	}
	
	public String Decompile(int indent) {
		String s = nameId == null ? "" : nameId.Decompile(indent);
		s += "(";
		
		for (int i = 0; i < args.size(); i++) {
			s += args.elementAt(i).Decompile(indent);
			if (i < args.size() - 1) {
				s += ", ";
			}
		}
		
		s += ")";
		
		return s;
	}
	
	public Vector<CFExpression> getArgs() {
		return args;
	}
	
	public String getName() {
		return nameId == null ? "" : nameId.Decompile(0);
	}
	
	public CFIdentifier getIdentifier() {
		return nameId;
	}
	
	public CFIdentifier getNameId() {
		return nameId;
	}
	
}
