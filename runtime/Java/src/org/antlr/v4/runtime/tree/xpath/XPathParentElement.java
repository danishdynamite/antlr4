package org.antlr.v4.runtime.tree.xpath;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;

public class XPathParentElement extends XPathElement {
	protected int ruleIndex;

	public XPathParentElement(int ruleIndex) {
		super("");
		this.ruleIndex = ruleIndex;
	}

	@Override
	public Collection<ParseTree> evaluate(ParseTree t) {
		List<ParseTree> nodes = new ArrayList<ParseTree>();
		ParseTree parent = t.getParent();
		if (parent != null) {
			if (ruleIndex == -1 
					|| (parent instanceof RuleNode && ((RuleNode) parent).getRuleContext().getRuleIndex() == ruleIndex)) {
				nodes.add(t.getParent());
			}
		}
		return nodes;
	}
}
