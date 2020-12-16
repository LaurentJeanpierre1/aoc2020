// Generated from /home/jeanpierre/temp/adventOfCode2020/src/day7/bags.g4 by ANTLR 4.9
package day7;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link bagsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface bagsVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link bagsParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(bagsParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link bagsParser#fichier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFichier(bagsParser.FichierContext ctx);
	/**
	 * Visit a parse tree produced by {@link bagsParser#ligne}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLigne(bagsParser.LigneContext ctx);
	/**
	 * Visit a parse tree produced by {@link bagsParser#content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitContent(bagsParser.ContentContext ctx);
	/**
	 * Visit a parse tree produced by {@link bagsParser#bag_name}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBag_name(bagsParser.Bag_nameContext ctx);
	/**
	 * Visit a parse tree produced by {@link bagsParser#bag}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBag(bagsParser.BagContext ctx);
}