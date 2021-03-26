// Generated from LabeledExpr.g4 by ANTLR 4.9.2
package com.hwloser.calculator.labeled;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LabeledExprParser}.
 */
public interface LabeledExprListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LabeledExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(LabeledExprParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LabeledExprParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(LabeledExprParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LabeledExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(LabeledExprParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link LabeledExprParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(LabeledExprParser.ExprContext ctx);
}