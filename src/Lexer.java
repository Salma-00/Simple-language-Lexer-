import java.io.*;
enum TokenType{WRITE,READ,IF,ELSE,RETURN,BEGIN,END,MAIN,STRING,INT,REAL,SEMI,COMMA,LPAREN,RPAREN,PLUS,MINUS,MULT,DIV,EQ,TESTEQ,TESTNOTEQ,QUOTEDSTRING,IDENTIFIER,NUMBER,COMMENT}
class Token
{
	String text;
	int begin;
	int end;
	TokenType type;
	public Token(TokenType type,String text,int begin,int end)
	{
		this.type = type;
		this.text = text;
		this.begin = begin;
		this.end = end;
	}
	public String toString()
	{
		return String.format("[%s,%s,%d,%d]",type,text,begin,end);
	}
} 


public class Lexer {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	public static void main(String []args) throws IOException
	{
		String msg = "My age is 30";
		FileReader r = new FileReader("in.txt");
		PrintWriter pw = new PrintWriter ("out.txt");
		Lexer l = new Lexer(r);
		Token tok;
		while((tok = l.getNextToken()) != null)
			pw.println(tok);
		pw.close();
		r.close();
	}
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexer (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexer (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexer () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NO_ANCHOR,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NOT_ACCEPT,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NOT_ACCEPT,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NOT_ACCEPT,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NOT_ACCEPT,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NOT_ACCEPT,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"28:9,32,33,28:2,32,28:18,32,26,27,28:5,18,19,22,20,17,21,28,23,31:10,24,16," +
"28,25,28:3,6,13,29,7,5,8,14,29,3,29:2,9,15,12,29:3,2,10,4,11,29,1,29:3,28:4" +
",30,28,29:26,28:5,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,69,
"0,1,2,1:7,3,4,5,6,7,1:4,6:10,8,9,10,11,12,13,14,8,15,12,16,17,1,18,19,20,21" +
",22,23,24,25,26,27,28,29,30,31,32,33,34,35,6,36,37,38,39,40,6,41,42")[0];

	private int yy_nxt[][] = unpackFromString(43,34,
"1,2,57,31,66,48,66:4,67,66:2,68,66,58,3,4,5,6,7,8,9,10,11,32,35,38,41,66:2," +
"12,30:2,-1:35,66,59,66:13,-1:13,66,60,61,-1:24,14,-1:36,15,-1:39,12,-1:3,66" +
":15,-1:13,66,60,61,-1:24,36,-1:12,36:21,49,36:10,-1:33,30:2,-1,66:7,13,66:3" +
",34,66:3,-1:13,66,60,61,-1:27,16,-1:9,33:26,18,33:5,-1:2,66:3,19,66:11,-1:1" +
"3,66,60,61,-1:27,17,-1:9,66:6,20,66:8,-1:13,66,60,61,-1:3,36:21,39,29,36:9," +
"-1:2,66:6,21,66,22,66:6,-1:13,66,60,61,-1:3,66:4,23,66:10,-1:13,66,60,61,-1" +
":3,66:11,24,66:3,-1:13,66,60,61,-1:3,66:4,25,66:10,-1:13,66,60,61,-1:3,66:1" +
"1,26,66:3,-1:13,66,60,61,-1:3,66:11,27,66:3,-1:13,66,60,61,-1:3,66:13,28,66" +
",-1:13,66,60,61,-1:3,66:8,51,66:2,37,66:3,-1:13,66,60,61,-1:3,36:21,39,36:1" +
"0,-1:2,66:3,64,66,40,66:9,-1:13,66,60,61,-1:3,66:9,42,66:5,-1:13,66,60,61,-" +
"1:3,66:2,43,66:12,-1:13,66,60,61,-1:3,66:3,44,66:11,-1:13,66,60,61,-1:3,66:" +
"2,45,66:12,-1:13,66,60,61,-1:3,66,46,66:13,-1:13,66,60,61,-1:3,66:11,47,66:" +
"3,-1:13,66,60,61,-1:3,66:4,50,66:10,-1:13,66,60,61,-1:3,66:5,52,66:9,-1:13," +
"66,60,61,-1:3,66:2,53,66:12,-1:13,66,60,61,-1:3,61:15,-1:13,61:3,-1:3,66,65" +
",66:13,-1:13,66,60,61,-1:3,66:13,54,66,-1:13,66,60,61,-1:3,66:10,55,66:4,-1" +
":13,66,60,61,-1:3,66:2,56,66:12,-1:13,66,60,61,-1:3,66:3,62,66:11,-1:13,66," +
"60,61,-1:3,66:4,63,66:10,-1:13,66,60,61,-1:2");

	public Token getNextToken ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 0:
						{}
					case -2:
						break;
					case 1:
						
					case -3:
						break;
					case 2:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -4:
						break;
					case 3:
						{return new Token (TokenType.SEMI,yytext(),yychar,yychar+yytext().length());}
					case -5:
						break;
					case 4:
						{return new Token (TokenType.COMMA,yytext(),yychar,yychar+yytext().length());}
					case -6:
						break;
					case 5:
						{return new Token (TokenType.LPAREN,yytext(),yychar,yychar+yytext().length());}
					case -7:
						break;
					case 6:
						{return new Token (TokenType.RPAREN,yytext(),yychar,yychar+yytext().length());}
					case -8:
						break;
					case 7:
						{return new Token (TokenType.PLUS,yytext(),yychar,yychar+yytext().length());}
					case -9:
						break;
					case 8:
						{return new Token (TokenType.MINUS,yytext(),yychar,yychar+yytext().length());}
					case -10:
						break;
					case 9:
						{return new Token (TokenType.MULT,yytext(),yychar,yychar+yytext().length());}
					case -11:
						break;
					case 10:
						{return new Token (TokenType.DIV,yytext(),yychar,yychar+yytext().length());}
					case -12:
						break;
					case 11:
						{System.out.println("error");}
					case -13:
						break;
					case 12:
						{return new Token (TokenType.NUMBER,yytext(),yychar,yychar+yytext().length());}
					case -14:
						break;
					case 13:
						{return new Token (TokenType.IF,yytext(),yychar,yychar+yytext().length());}
					case -15:
						break;
					case 15:
						{return new Token (TokenType.EQ,yytext(),yychar,yychar+yytext().length());}
					case -16:
						break;
					case 16:
						{return new Token (TokenType.TESTEQ,yytext(),yychar,yychar+yytext().length());}
					case -17:
						break;
					case 17:
						{return new Token (TokenType.TESTNOTEQ,yytext(),yychar,yychar+yytext().length());}
					case -18:
						break;
					case 18:
						{return new Token (TokenType.QUOTEDSTRING,yytext(),yychar,yychar+yytext().length());}
					case -19:
						break;
					case 19:
						{return new Token (TokenType.INT,yytext(),yychar,yychar+yytext().length());}
					case -20:
						break;
					case 20:
						{return new Token (TokenType.END,yytext(),yychar,yychar+yytext().length());}
					case -21:
						break;
					case 21:
						{return new Token (TokenType.READ,yytext(),yychar,yychar+yytext().length());}
					case -22:
						break;
					case 22:
						{return new Token (TokenType.REAL,yytext(),yychar,yychar+yytext().length());}
					case -23:
						break;
					case 23:
						{return new Token (TokenType.ELSE,yytext(),yychar,yychar+yytext().length());}
					case -24:
						break;
					case 24:
						{return new Token (TokenType.MAIN,yytext(),yychar,yychar+yytext().length());}
					case -25:
						break;
					case 25:
						{return new Token (TokenType.WRITE,yytext(),yychar,yychar+yytext().length());}
					case -26:
						break;
					case 26:
						{return new Token (TokenType.BEGIN,yytext(),yychar,yychar+yytext().length());}
					case -27:
						break;
					case 27:
						{return new Token (TokenType.RETURN,yytext(),yychar,yychar+yytext().length());}
					case -28:
						break;
					case 28:
						{return new Token (TokenType.STRING,yytext(),yychar,yychar+yytext().length());}
					case -29:
						break;
					case 29:
						{return new Token (TokenType.COMMENT,yytext(),yychar,yychar+yytext().length());}
					case -30:
						break;
					case 30:
						{}
					case -31:
						break;
					case 31:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -32:
						break;
					case 32:
						{System.out.println("error");}
					case -33:
						break;
					case 34:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -34:
						break;
					case 35:
						{System.out.println("error");}
					case -35:
						break;
					case 37:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -36:
						break;
					case 38:
						{System.out.println("error");}
					case -37:
						break;
					case 40:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -38:
						break;
					case 41:
						{System.out.println("error");}
					case -39:
						break;
					case 42:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -40:
						break;
					case 43:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -41:
						break;
					case 44:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -42:
						break;
					case 45:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -43:
						break;
					case 46:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -44:
						break;
					case 47:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -45:
						break;
					case 48:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -46:
						break;
					case 50:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -47:
						break;
					case 51:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -48:
						break;
					case 52:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -49:
						break;
					case 53:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -50:
						break;
					case 54:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -51:
						break;
					case 55:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -52:
						break;
					case 56:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -53:
						break;
					case 57:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -54:
						break;
					case 58:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -55:
						break;
					case 59:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -56:
						break;
					case 60:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -57:
						break;
					case 61:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -58:
						break;
					case 62:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -59:
						break;
					case 63:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -60:
						break;
					case 64:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -61:
						break;
					case 65:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -62:
						break;
					case 66:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -63:
						break;
					case 67:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -64:
						break;
					case 68:
						{return new Token (TokenType.IDENTIFIER,yytext(),yychar,yychar+yytext().length());}
					case -65:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
