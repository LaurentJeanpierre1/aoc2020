// Generated from /home/jeanpierre/temp/adventOfCode2020/src/day7/bags.g4 by ANTLR 4.9
package day7;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link bagsParser}.
 */
public interface bagsListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link bagsParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(bagsParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link bagsParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(bagsParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link bagsParser#fichier}.
	 * @param ctx the parse tree
	 */
	void enterFichier(bagsParser.FichierContext ctx);
	/**
	 * Exit a parse tree produced by {@link bagsParser#fichier}.
	 * @param ctx the parse tree
	 */
	void exitFichier(bagsParser.FichierContext ctx);
	/**
	 * Enter a parse tree produced by {@link bagsParser#ligne}.
	 * @param ctx the parse tree
	 */
	void enterLigne(bagsParser.LigneContext ctx);
	/**
	 * Exit a parse tree produced by {@link bagsParser#ligne}.
	 * @param ctx the parse tree
	 */
	void exitLigne(bagsParser.LigneContext ctx);
	/**
	 * Enter a parse tree produced by {@link bagsParser#content}.
	 * @param ctx the parse tree
	 */
	void enterContent(bagsParser.ContentContext ctx);
	/**
	 * Exit a parse tree produced by {@link bagsParser#content}.
	 * @param ctx the parse tree
	 */
	void exitContent(bagsParser.ContentContext ctx);
	/**
	 * Enter a parse tree produced by {@link bagsParser#bag_name}.
	 * @param ctx the parse tree
	 */
	void enterBag_name(bagsParser.Bag_nameContext ctx);
	/**
	 * Exit a parse tree produced by {@link bagsParser#bag_name}.
	 * @param ctx the parse tree
	 */
	void exitBag_name(bagsParser.Bag_nameContext ctx);
	/**
	 * Enter a parse tree produced by {@link bagsParser#bag}.
	 * @param ctx the parse tree
	 */
	void enterBag(bagsParser.BagContext ctx);
	/**
	 * Exit a parse tree produced by {@link bagsParser#bag}.
	 * @param ctx the parse tree
	 */
	void exitBag(bagsParser.BagContext ctx);
}