package Test2;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class rda {
    public static ArrayList<String> clumps = new ArrayList<String>();
    public static ArrayList<String> tokenized_clumps = new ArrayList<String>();
    public static void main(String[] args) throws IOException, LogException {
        HashMap<String, String> tokens = new HashMap<>();
        tokens.put("+", "ADD_OP");
        tokens.put("-", "SUB_OP");
        tokens.put("*", "MULT_OP");
        tokens.put("/", "DIV_OP");
        tokens.put("%", "MOD_OP");
        tokens.put("==", "EQ_OP");
        tokens.put("!=", "NEQ_OP");
        tokens.put("=", "ASSIGN_OP");
        tokens.put("<", "LT_OP");
        tokens.put("<=", "LTE_OP");
        tokens.put(">", "GT_OP");
        tokens.put(">=", "GTE_OP");
        tokens.put("&&", "AND_OP");
        tokens.put("||", "OR_OP");
        tokens.put("VARIABLE_ID", "VAR_ID");
        tokens.put("INTEGER_LITERAL", "INT_LIT");
        tokens.put("FLOATING_LITERAL", "FLT_LIT");
        tokens.put("{", "LCB");
        tokens.put("}", "RCB");
        tokens.put("(", "LP");
        tokens.put(")", "RP");
        tokens.put(";", "SEMICOLON");
        tokens.put("if", "IF_STMT");
        tokens.put("else", "ELSE_STMT");
        tokens.put("while", "WHILE_STMT");
        tokens.put("DataType", "DATA_TYPE");

        String filename = "C:\\Users\\zeroz\\Dropbox\\GSU\\2023\\Spring\\CSC 4330\\exams\\Test2\\test_document.txt";
        
        
        FileReader reader = new FileReader(filename);
        int character;
        
       
        String temp = "";
       while ((character = reader.read()) != -1) {
            char currentChar = (char) character;
            //System.out.print(currentChar);
           
            if (currentChar == '('){
                temp += currentChar;
                clumps.add(temp);
                temp = "";
            } else if (currentChar == ')'){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            } else if (currentChar == ';'){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            } else if (currentChar == '{'){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            }
            else if (currentChar == '}'){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            }
            else if (currentChar == ','){
                clumps.add(temp);
                clumps.add(String.valueOf(currentChar));
                temp = "";
            }
            
            else  if (currentChar!= ' '){
                temp += currentChar;
            }
            else {
                    clumps.add(temp);
                    temp = "";
                }
            
          
       }
      
       clumps.add(temp);
       //System.out.println();
       for (int i = 0; i<clumps.size(); i++){
            clumps.set(i, clumps.get(i).replaceAll("\\s+", ""));
            
       }
       
       for (int i = 0; i<clumps.size(); i++){
        if(clumps.get(i).equals("") || clumps.get(i).equals(" ")){
            clumps.remove(i);
        }
        
        }
        for (int i = 0; i<clumps.size(); i++){
            if(clumps.get(i).equals("") || clumps.get(i).equals("\n")){
                clumps.remove(i);
            }
            
            }
   
   for (int i = 0; i<clumps.size(); i++){
    if(clumps.get(i).equals("") || clumps.get(i).equals("\t")){
        clumps.remove(i);
       
    }
   }
    
        //System.out.println("");
   
   for (int i = 0; i<clumps.size(); i++){
        tokenized_clumps.add(tokenizer(clumps.get(i)));
   }
   rda();
   System.out.println("Provided code is compatible with language");
//    for (int i = 0; i<clumps.size();i++){
//         System.out.print(tokenized_clumps.get(i) + "  ");
//         System.out.println(clumps.get(i));
//    }

}
public static String tokenizer(String x){
    HashMap<String, String> tokens = new HashMap<>();
    tokens.put("+", "ADD_OP");
    tokens.put("-", "SUB_OP");
    tokens.put("*", "MULT_OP");
    tokens.put("/", "DIV_OP");
    tokens.put("%", "MOD_OP");
    tokens.put("==", "EQ_OP");
    tokens.put("!=", "NEQ_OP");
    tokens.put("=", "ASSIGN_OP");
    tokens.put("<", "LT_OP");
    tokens.put("<=", "LTE_OP");
    tokens.put(">", "GT_OP");
    tokens.put(">=", "GTE_OP");
    tokens.put("&&", "AND_OP");
    tokens.put("||", "OR_OP");
    tokens.put("VARIABLE_ID", "VAR_ID");
    tokens.put("INTEGER_LITERAL", "INT_LIT");
    tokens.put("FLOATING_LITERAL", "FLT_LIT");
    tokens.put("{", "LCB");
    tokens.put("}", "RCB");
    tokens.put("(", "LP");
    tokens.put(")", "RP");
    tokens.put(";", "SEMICOLON");
    tokens.put("if", "IF_STMT");
    tokens.put("else", "ELSE_STMT");
    tokens.put("while", "WHILE_STMT");
    tokens.put("DataType", "DATA_TYPE");
    tokens.put(",", "COLON");
    Pattern pattern = Pattern.compile("[0-9]+");
    Pattern pattern2 = Pattern.compile("[a-zA-Z]+");
    Matcher matcher = pattern.matcher(x);
    Matcher matcher2 = pattern2.matcher(x);
    if (matcher.matches()){
        if (x.contains(".")){
            return tokens.get("FLOATING_LITERAL");
        } else {
            return tokens.get("INTEGER_LITERAL");
        }
    }
    else if (x.equals("DataType")){return tokens.get("DataType");}
    else if (x.equals("if")){return tokens.get("if");}
    else if (x.equals("else")){return tokens.get("else");}
    else if (x.equals("while")){return tokens.get("while");}
    else if (matcher2.matches()){
        return tokens.get("VARIABLE_ID");
    } 
    else if (x.equals("||")){return tokens.get("||");}
    else if (x.equals("&&")){return tokens.get("&&");}
    else if (x.equals(">=")){return tokens.get(">=");}
    else if (x.equals(">")){return tokens.get(">");}
    else if (x.equals("<=")){return tokens.get("<=");}
    else if (x.equals("<")){return tokens.get("<");}
    else if (x.equals("=")){return tokens.get("=");}
    else if (x.equals("==")){return tokens.get("==");}
    else if (x.equals("%")){return tokens.get("%");}
    else if (x.equals("/")){return tokens.get("/");}
    else if (x.equals("*")){return tokens.get("*");}
    else if (x.equals("-")){return tokens.get("-");}
    else if (x.equals("+")){return tokens.get("+");}
    else if (x.equals("{")){return tokens.get("{");}
    else if (x.equals("}")){return tokens.get("}");}
    else if (x.equals("(")){return tokens.get("(");}
    else if (x.equals(")")){return tokens.get(")");}
    else if (x.equals(";")){return tokens.get(";");}
    else if (x.equals(",")){return tokens.get(",");}
    
    else { return "UNKOWN";}
}
public static int index = 0;
public static void rda () throws LogException{
    while (index < tokenized_clumps.size()){
        stmt();
    }
}

public static void stmt() throws LogException{
   if (tokenized_clumps.get(index).equals("WHILE_STMT")){
        cprint();
        cIndex();
        whileL();
   } else if (tokenized_clumps.get(index).equals("IF_STMT")){
        cprint();
        cIndex();
        ifS();
   } else if (tokenized_clumps.get(index).equals("VAR_ID")){
        cprint();
        cIndex();
        assign();
   } else if (tokenized_clumps.get(index).equals("LCB")){
        cprint();
        cIndex();
        block();
   } else if (tokenized_clumps.get(index).equals("DATA_TYPE")){
        cprint();
        cIndex();
        declare();
   } else {
        cprint();
        throw new LogException("Stmt error");
   }
}

public static void stmt_list() throws LogException{
    stmt();
    if (!tokenized_clumps.get(index).equals("SEMICOLON")){
        throw new LogException("missing semicolon in stmt list");
    }
    if (index < tokenized_clumps.size()-2){
    while (tokenized_clumps.get(index).equals("SEMICOLON")){
        if (tokenized_clumps.get(index + 1).equals("RCB")){
            cprint();
            cIndex();
            } else {
                cprint();
                cIndex();
                stmt();
                if  (!tokenized_clumps.get(index).equals("SEMICOLON")){
                    throw new LogException("missing semicolon in stmt list");
                }
        
        // if (index < tokenized_clumps.size()-2){
        //     System.out.println(index);
        //     if (tokenized_clumps.get(index + 1).equals("RCB")){
        //         cprint();
        //         cIndex();
        //     } else {
        //         cprint();
        //         cIndex();
        //         stmt();
        //     }
        // } else {
        //     cprint();
        //     cIndex();
        // }
        // if ((index < tokenized_clumps.size()) && tokenized_clumps.get(index + 1).equals("RCB")){
        //     cprint();
        //     cIndex();
        // } else {
        //     cprint();
        // cIndex();
        // stmt();
        // }
        
    }}}
}

public static void whileL() throws LogException{
    if (!tokenized_clumps.get(index).equals("LP")){
        throw new LogException("While loop missing LP");
    } else {
        cprint();
        cIndex();
        bExpr();
        if (tokenized_clumps.get(index).equals("RP")){
            cprint();
            cIndex();
            if (tokenized_clumps.get(index).equals("LCB")){
                cprint();
                cIndex();
                block();
                if (tokenized_clumps.get(index).equals("RCB")){
                    cprint();
                    cIndex();
                } else {
                    throw new LogException("while loop block missing right bracket");
                }
            } else {
                throw new LogException("while loop block missing left bracket");
            }
        } else {
            throw new LogException("While loop missing RP");
        }
    }
}

public static void ifS() throws LogException{
    if (!tokenized_clumps.get(index).equals("LP")){
        throw new LogException("If statement missing LP");
    } else {
        cprint();
        cIndex();
        bExpr();
        if (tokenized_clumps.get(index).equals("RP")){
            cprint();
            cIndex();
            if (tokenized_clumps.get(index).equals("LCB")){
                cprint();
                cIndex();
                block();
                if (tokenized_clumps.get(index).equals("RCB")){
                    cprint();
                    cIndex();
                } else {
                    throw new LogException("if statement block missing right bracket");
                }
            } else {
                throw new LogException("if statement block missing left bracket");
            }
        } else {
            throw new LogException("if statement missing RP");
        }
    }
    System.out.println("test");
    
        if (tokenized_clumps.get(index).equals("ELSE_STMT")){
            System.out.println("test");
            
            
            cprint();
            cIndex();
            if (tokenized_clumps.get(index).equals("LCB")){
                cprint();
                cIndex();
                block();
                cprint();
                cIndex();
                if (tokenized_clumps.get(index).equals("RCB")){
                    cprint();
                    cIndex();
                } else {
                    throw new LogException("else statement missing RCB");
                }

            } else {throw new LogException("else statement missing LCB");}
        }
    
}

public static void block()throws LogException{
    stmt_list();
}

public static void declare()throws LogException{
    if (tokenized_clumps.get(index).equals("VAR_ID")){
        cprint();
        cIndex();
        if (index < tokenized_clumps.size()-2){
            while (tokenized_clumps.get(index).equals("COLON")){
                cprint();
                cIndex();
                if (tokenized_clumps.get(index).equals("VAR_ID")){
                    cprint();
                    cIndex();
                } else {
                    throw new LogException("declare statement error");
                }
            }
        }
    } else {
        throw new LogException("declare statement missing variable id");
    }
}

public static void assign()throws LogException{
    if (tokenized_clumps.get(index).equals("ASSIGN_OP")){
        cprint();
        cIndex();
        expr();
    } else {
        throw new LogException("Assignment operator missing");
    }
}

public static void expr()throws LogException{
    term();
    while (tokenized_clumps.get(index).equals("ADD_OP") || tokenized_clumps.get(index).equals("SUB_OP")){
        cprint();
        cIndex();
        term();
    }
}

public static  void term ()throws LogException{
    factor();
    while (tokenized_clumps.get(index).equals("MULT_OP") || tokenized_clumps.get(index).equals("DIV_OP") || tokenized_clumps.get(index).equals("MOD_OP")){
        cprint();
        cIndex();
        factor();
    }
}

public static void factor()throws LogException{
    if (tokenized_clumps.get(index).equals("VAR_ID") || tokenized_clumps.get(index).equals("INT_LIT") || tokenized_clumps.get(index).equals("FLT_LIT") || tokenized_clumps.get(index).equals("LP")){
        if (tokenized_clumps.get(index).equals("LP")){
            cprint();
            cIndex();
            expr();
            if (tokenized_clumps.get(index).equals("RP")){
                cprint();
                cIndex();
            } else {
                throw new LogException("RP missing in factor");
            }
        } else {
            cprint();
            cIndex();
        }
    } else {
        throw new LogException("factor error");
    }
}

public static void bExpr() throws LogException{
    bTerm();
    while (tokenized_clumps.get(index).equals("LT_OP") || tokenized_clumps.get(index).equals("LTE_OP") || tokenized_clumps.get(index).equals("GT_OP") || tokenized_clumps.get(index).equals("GTE_OP")){
        cprint();
        cIndex();
        bTerm();
    }
}

public static void bTerm()throws LogException{
    bAnd();
    while (tokenized_clumps.get(index).equals("EQ_OP") || tokenized_clumps.get(index).equals("NEQ_OP")){
        cprint();
        cIndex();
        bAnd();
    }
}

public static void bAnd()throws LogException{
    bOr();
    while (tokenized_clumps.get(index).equals("AND_OP")){
        cprint();
        cIndex();
        bAnd();
    }
}

public static void bOr()throws LogException{
    expr();
    while (tokenized_clumps.get(index).equals("OR_OP")){
        cprint();
        cIndex();
        expr();
    }
}

public static void cIndex() throws LogException{
    if (index == tokenized_clumps.size()){
        rda();
    } else {index++;}
}

public static class LogException extends Exception {
    public LogException() {
        super();
    }

    public LogException(String message) {
        super(message);
    }

    public LogException(String message, Throwable cause) {
        super(message, cause);
    }
}

public static void cprint (){
    System.out.print(tokenized_clumps.get(index) + "  ");
    System.out.println(clumps.get(index));
}

}
