// Generated from /home/jeanpierre/temp/adventOfCode2020/src/day7/bags.g4 by ANTLR 4.9
package day7;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class bagsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, WS=3, BAGS=4, CONTAIN=5, NO_OTHER_BAGS=6, INT=7, COLOR=8;
	public static final int
		RULE_start = 0, RULE_fichier = 1, RULE_ligne = 2, RULE_content = 3, RULE_bag_name = 4, 
		RULE_bag = 5;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "fichier", "ligne", "content", "bag_name", "bag"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'\n'", null, null, "'contain'", "'no other bags'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "WS", "BAGS", "CONTAIN", "NO_OTHER_BAGS", "INT", "COLOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "bags.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public bagsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public FichierContext fichier() {
			return getRuleContext(FichierContext.class,0);
		}
		public TerminalNode EOF() { return getToken(bagsParser.EOF, 0); }
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bagsVisitor ) return ((bagsVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			fichier();
			setState(13);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FichierContext extends ParserRuleContext {
		public List<LigneContext> ligne() {
			return getRuleContexts(LigneContext.class);
		}
		public LigneContext ligne(int i) {
			return getRuleContext(LigneContext.class,i);
		}
		public FichierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fichier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).enterFichier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).exitFichier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bagsVisitor ) return ((bagsVisitor<? extends T>)visitor).visitFichier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FichierContext fichier() throws RecognitionException {
		FichierContext _localctx = new FichierContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_fichier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(18);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COLOR) {
				{
				{
				setState(15);
				ligne();
				}
				}
				setState(20);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LigneContext extends ParserRuleContext {
		public Bag_nameContext bag_name;
		public ContentContext content;
		public Bag_nameContext bag_name() {
			return getRuleContext(Bag_nameContext.class,0);
		}
		public TerminalNode CONTAIN() { return getToken(bagsParser.CONTAIN, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public LigneContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ligne; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).enterLigne(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).exitLigne(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bagsVisitor ) return ((bagsVisitor<? extends T>)visitor).visitLigne(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LigneContext ligne() throws RecognitionException {
		LigneContext _localctx = new LigneContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_ligne);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(21);
			((LigneContext)_localctx).bag_name = bag_name();
			setState(22);
			match(CONTAIN);
			setState(23);
			((LigneContext)_localctx).content = content();
			setState(24);
			match(T__0);
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(25);
				match(T__1);
				}
			}

			 System.out.printf("%s -> %s\n", ((LigneContext)_localctx).bag_name.name, ((LigneContext)_localctx).content.res.toString()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContentContext extends ParserRuleContext {
		public List<Content> res;
		public BagContext bag;
		public List<BagContext> l = new ArrayList<BagContext>();
		public TerminalNode NO_OTHER_BAGS() { return getToken(bagsParser.NO_OTHER_BAGS, 0); }
		public List<BagContext> bag() {
			return getRuleContexts(BagContext.class);
		}
		public BagContext bag(int i) {
			return getRuleContext(BagContext.class,i);
		}
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).enterContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).exitContent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bagsVisitor ) return ((bagsVisitor<? extends T>)visitor).visitContent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_content);
		int _la;
		try {
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NO_OTHER_BAGS:
				enterOuterAlt(_localctx, 1);
				{
				setState(30);
				match(NO_OTHER_BAGS);
				 ((ContentContext)_localctx).res =  new ArrayList<Content>(); 
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(33); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(32);
					((ContentContext)_localctx).bag = bag();
					((ContentContext)_localctx).l.add(((ContentContext)_localctx).bag);
					}
					}
					setState(35); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==INT );
				 ((ContentContext)_localctx).res =  (((ContentContext)_localctx).l).stream().map(b->b.aBag).collect(Collectors.toList()); 
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Bag_nameContext extends ParserRuleContext {
		public String name;
		public Token COLOR;
		public List<Token> l = new ArrayList<Token>();
		public TerminalNode BAGS() { return getToken(bagsParser.BAGS, 0); }
		public List<TerminalNode> COLOR() { return getTokens(bagsParser.COLOR); }
		public TerminalNode COLOR(int i) {
			return getToken(bagsParser.COLOR, i);
		}
		public Bag_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bag_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).enterBag_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).exitBag_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bagsVisitor ) return ((bagsVisitor<? extends T>)visitor).visitBag_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bag_nameContext bag_name() throws RecognitionException {
		Bag_nameContext _localctx = new Bag_nameContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bag_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				((Bag_nameContext)_localctx).COLOR = match(COLOR);
				((Bag_nameContext)_localctx).l.add(((Bag_nameContext)_localctx).COLOR);
				}
				}
				setState(44); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==COLOR );
			setState(46);
			match(BAGS);
			 ((Bag_nameContext)_localctx).name =  ((Bag_nameContext)_localctx).l.stream().map(c->c.getText()).collect(Collectors.joining()); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BagContext extends ParserRuleContext {
		public Content aBag;
		public Token INT;
		public Bag_nameContext bag_name;
		public TerminalNode INT() { return getToken(bagsParser.INT, 0); }
		public Bag_nameContext bag_name() {
			return getRuleContext(Bag_nameContext.class,0);
		}
		public BagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bag; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).enterBag(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof bagsListener ) ((bagsListener)listener).exitBag(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof bagsVisitor ) return ((bagsVisitor<? extends T>)visitor).visitBag(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BagContext bag() throws RecognitionException {
		BagContext _localctx = new BagContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_bag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			((BagContext)_localctx).INT = match(INT);
			setState(50);
			((BagContext)_localctx).bag_name = bag_name();
			 ((BagContext)_localctx).aBag =  new Content(((BagContext)_localctx).bag_name.name, (((BagContext)_localctx).INT!=null?Integer.valueOf(((BagContext)_localctx).INT.getText()):0)); 
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\n8\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\3\2\3\2\3\2\3\3\7\3\23\n\3\f\3\16\3"+
		"\26\13\3\3\4\3\4\3\4\3\4\3\4\5\4\35\n\4\3\4\3\4\3\5\3\5\3\5\6\5$\n\5\r"+
		"\5\16\5%\3\5\3\5\5\5*\n\5\3\6\6\6-\n\6\r\6\16\6.\3\6\3\6\3\6\3\7\3\7\3"+
		"\7\3\7\3\7\2\2\b\2\4\6\b\n\f\2\2\2\66\2\16\3\2\2\2\4\24\3\2\2\2\6\27\3"+
		"\2\2\2\b)\3\2\2\2\n,\3\2\2\2\f\63\3\2\2\2\16\17\5\4\3\2\17\20\7\2\2\3"+
		"\20\3\3\2\2\2\21\23\5\6\4\2\22\21\3\2\2\2\23\26\3\2\2\2\24\22\3\2\2\2"+
		"\24\25\3\2\2\2\25\5\3\2\2\2\26\24\3\2\2\2\27\30\5\n\6\2\30\31\7\7\2\2"+
		"\31\32\5\b\5\2\32\34\7\3\2\2\33\35\7\4\2\2\34\33\3\2\2\2\34\35\3\2\2\2"+
		"\35\36\3\2\2\2\36\37\b\4\1\2\37\7\3\2\2\2 !\7\b\2\2!*\b\5\1\2\"$\5\f\7"+
		"\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2\2&\'\3\2\2\2\'(\b\5\1\2(*\3"+
		"\2\2\2) \3\2\2\2)#\3\2\2\2*\t\3\2\2\2+-\7\n\2\2,+\3\2\2\2-.\3\2\2\2.,"+
		"\3\2\2\2./\3\2\2\2/\60\3\2\2\2\60\61\7\6\2\2\61\62\b\6\1\2\62\13\3\2\2"+
		"\2\63\64\7\t\2\2\64\65\5\n\6\2\65\66\b\7\1\2\66\r\3\2\2\2\7\24\34%).";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}