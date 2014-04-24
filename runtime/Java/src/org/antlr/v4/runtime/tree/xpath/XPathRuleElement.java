package org.antlr.v4.runtime.tree.xpath;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;
import org.antlr.v4.runtime.tree.Trees;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XPathRuleElement extends XPathElement implements XPathFiltering {
	protected int ruleIndex;
	protected String filterText;
	
	public XPathRuleElement(String ruleName, int ruleIndex) {
		super(ruleName);
		this.ruleIndex = ruleIndex;
	}

	@Override
	public void setTextFilter(String text) {
		this.filterText = text;
	}
	
	@Override
	public Collection<ParseTree> evaluate(ParseTree t) {
		List<ParseTree> nodes = new ArrayList<ParseTree>();
		for (Tree c : Trees.getChildren(t)) {
			if (c instanceof ParserRuleContext) {
				ParserRuleContext ctx = (ParserRuleContext) c;
				boolean matched = ctx.getRuleIndex() == ruleIndex && (filterText == null || ctx.getText().equals(filterText));
				if ((matched && !invert) || (!matched && invert)) {
					nodes.add(ctx);
				}
			}
		}
		return nodes;
	}
}
