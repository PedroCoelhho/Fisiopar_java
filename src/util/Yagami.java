package util;

import engine.DataSource;
import engine.MysqlConn;
import java.awt.Container;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.Usuario;
import view.Login;
import view.Main;

/**
 * Classe Global do Sistema - Yagami
 * Todo e qualquer método de uso comum deve ser colocado aqui como public static
 * Assim os métodos serão visíveis em todo o sistema
 * 
 * Também temos aqui duas instâncias globais:
 * 
 * Yagami YG - Instância a propria classe para acesso de atributos globais, como
 * por exemplo, a publicId, usada para "transportar" um valor de uma view para
 * outra... Yagami.YG...
 * 
 * Usuario usuario - Instância global do usuário logado, para acessar valores
 * úteis, como o nome ou se é Adminstrador ou não... Yagami.usuario...
 * 
 * @author Juan Galvão
 */
public class Yagami {
    // Duas instâncias globais
    
    /** Instância global da propria classe Yagami para acesso de atributos */
    public static final Yagami YG = new Yagami();
    
    /** Instância global de usuário para acesso de valores comuns */
    public static Usuario usuario = new Usuario();
    
    // Variável publicId para transporte de ID entre views
    private int publicId;

    private Object publicObject;
    
    /** 
     * getPublicId()
     * @return int */
    public int getPublicId() {
        return publicId;
    }

    /** 
     * setPublicId(int publicId)
     * @param publicId ID à ser armazenada
     */
    public void setPublicId(int publicId) {
        this.publicId = publicId;
    }

    public Object getPublicObject() {
        return publicObject;
    }

    public void setPublicObject(Object publicObject) {
        this.publicObject = publicObject;
    }
    
    
    //------------------------------------------------------------------------//
    // Métodos Divinos                                                        //
    // Coloque seu nome ao criar um método daora e seja lembrado como um Deus!//
    //------------------------------------------------------------------------//
    
    /**
     * Recorta o tanto de chars escolhidos de uma String e retorna em outra
     * @param string String que será recortada
     * @param chars Número do tanto de carácteres que serão separados
     * @return String com os n primeiros carácters agrupados
     * @author Juan Galvão
     */
    public static String recortarString(String string, int chars) {
        String stringFinal = "";
        
        if(string.length() < chars) {
            // IMPOSSIBLE
            stringFinal = "Impossível";
        } else {
            for(int i = 0; i < chars; i++) {
                stringFinal = stringFinal + string.charAt(i);
            }
        }
        
        return stringFinal;
    }
    
    /**
     * Método para abrir uma tela a partir de seu código (Name)
     * @param codigo Propriedade name da Tela
     * @param parent Por padrão é o Main.desktop
     * @author Juan Galvão
     */
    public static void abrirTelaCodigo(String codigo, Container parent) {
        boolean achou = false;
        for(JInternalFrame tela : DataSource.DS.telas) {
            if(tela.getName().equals(codigo)) {
                exibirTela(tela, parent, false);
                achou = true;
            }            
        }
        if(!achou) {
                JOptionPane.showMessageDialog(null, "Nenhuma tela encontrada");
            }
    }
    
    /**
     * Método para converter uma String em sql.Time
     * @param st String que será convertida
     * @return Objeto java.sql.Time convertido
     * @throws ParseException 
     * @author Matt Ball
     * @see https://stackoverflow.com/questions/2731443/cast-a-string-to-sql-time
     */
    public static java.sql.Time stringToSqlTime(String st) throws ParseException {
        String s = st /* your date string here */;
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        long ms = sdf.parse(s).getTime();
        Time t = new Time(ms);
        return t;
    }
    
    /**
     * Método para deslogar do Sistema
     * Zera a instância global de usuário, fecha o Form principal e abre o Form de Login
     * @param m
     * @throws java.sql.SQLException
     */
    public static void logout(Main m) throws SQLException {
        usuario = new Usuario();
        MysqlConn.closeConnection();
        Main main = m;
        new Login().setVisible(true);
        main.dispose();
    }
    
    /**
     * Método exit customizado - Fecha a conexão antes de encessar o sistema
     * @param cod 0: saída esperada 1: saída inexperada
     * @throws SQLException
     * @author Juan Galvão
     */
    public static void exit(int cod) throws SQLException {
        MysqlConn.closeConnection();
        java.lang.System.exit(cod);
    
    }
    
    /**
     * Mensagem de erro comum - Apenas para economizar tempo
     * @param ex Qualquer tipo de exception para que apareça na mensagem
     * @author Juan Galvão
     */
    public static void mensagemErro(Exception ex) {
        JOptionPane.showMessageDialog(
                null,
                "Um erro foi encontrado!\n" + ex,
                "Erro desconhecido!",
                JOptionPane.ERROR_MESSAGE
        );
    }
    
    /**
     * Converte qualquer data para o formato desejado
     * Feito apenas para fins de exibição
     * 
     * @param d Data que será formatada
     * @param formato Formato desejado para nova data
     * @return String
     * @throws ParseException 
     * @author Juan Galvão
     */
    public static String convertDate(Date d, String formato) throws ParseException {
        // Cria o objeto do formato de data a partir do parametro formato
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        
        // Recebe a data
        java.sql.Date date = d;
        
        // Converte a data
        String dateString = sdf.format(date);
        
        return dateString;
    }
    
    /**
     * Converte uma String para sql.Date
     * 
     * @param text String contendo a data
     * @return Date
     * @throws ParseException 
     * @author Juan Galvão
     */
    public static Date stringToDate(String text) throws ParseException {
        // Recebe a String
        String startDate = text;
        
        // Define o formato da conversão
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        
        // Converte a data
        java.util.Date date = sdf1.parse(startDate);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        
        // Retorna a data
        return sqlStartDate;
    }
    
    /**
     * Método antigo de redimensionamento de colunas para uma tabela.
     * Deve ser usado junto a uma variável float[] contendo os valores em
     * porcentagem.
     * 
     * @deprecated Substituída pela Yagami.resizeColumns2 por melhor usabildiade
     * @param tabela Armazena uma JTable
     * @param columnWidthPercentage Recebe a variável float[] com os valores
     * @author Internet (Encontrado por: Juan Galvão)
     */
    public static void resizeColumns(JTable tabela, float[] columnWidthPercentage) {
        int tW = tabela.getWidth();
        TableColumn column;
        TableColumnModel jTableColumnModel = tabela.getColumnModel();
        int cantCols = jTableColumnModel.getColumnCount();
        for (int i = 0; i < cantCols; i++) {
            column = jTableColumnModel.getColumn(i);
            int pWidth = Math.round(columnWidthPercentage[i] * tW);
            column.setPreferredWidth(pWidth);
        }
    }
    
    /**
     * Método de redimensionamento de colunas para uma tabela
     * @param table Recebe uma JTable
     * @param tablePreferredWidth Recebe a Width da JTable
     * @param percentages Divisão das colunas (Ex: 90, 05, 05), totalizando 100%
     * @author Internet (Encontrado por: Juan Galvão)
     */
    public static void resizeColumns2(JTable table, int tablePreferredWidth, double... percentages) {
        double total = 0;
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            total += percentages[i];
        }

        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth((int) (tablePreferredWidth * (percentages[i] / total)));
        }
    }
    
    /**
     * Método para converter String para uma hash MD5
     * @param entry String que será criptografada
     * @return String
     * @throws NoSuchAlgorithmException 
     * @author Juan Galvão
     */
    public static String md5(String entry) throws NoSuchAlgorithmException {
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.update(entry.getBytes(),0,entry.length());
        String hash = new BigInteger(1,m.digest()).toString(16);
        
        return hash;
    }
    
    /**
     * Metodo usado para validacao do Cpf
     * @param CPF String do CPF para ser válidado
     * @return Boolean indicando se o CPF é falso ou não
     * @author MaiKone 
     */
    
    public static boolean isCPF(String CPF) {
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000")
                || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")
                || (CPF.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
 
    /**
     * Método para preencher a instância global do usuário
     * 
     * @param id ID do usuário
     * @param nome Nome do usuário
     * @param user Login do usuário
     * @param senha Senha do usuário em MD5
     * @param nivelAcesso Nível de acesso para permissões de tela
     * @author Juan Galvão
     */
    public static void lerUsuario(int id, String nome, String user, String senha, int nivelAcesso) {
        usuario.setId(id);
        usuario.setNome(nome);
        usuario.setUsuario(user);
        usuario.setSenha(senha);
        usuario.setNivelAcesso(nivelAcesso);
    }
    
    
    // Métodos CORE
    /**
     * Método usado para exibir uma tela no Desktop Virtual da classe Main
     * 
     * @param frame Classe JInternalFrame contendo a tela a ser exibida
     * @param parent Container aonde o JInternalFrame aparecerá
     * @param maximized Verificação para saber se o Frame abrirá maximizado
     * 
     * @author Juan Galvão
     */
    public static void exibirTela(JInternalFrame frame, Container parent, boolean maximized) {
        // Deixa o frame visível
        frame.setVisible(true);
        
        // Requisita o foco no frame
        frame.requestFocusInWindow();
        
        // Adiciona o frame ao parent
        parent.add(frame);
        try {
            // Passa o valor para maximizar ou não o frame
            frame.setMaximum(maximized);
            
            // Deixa o frame ativo ao abrir
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) { }
        
    }
    
    public static boolean data(String data) {
        try {
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
           
            sdf.setLenient(false);
            
            sdf.parse(data);
            
            return true;
        } catch (ParseException ex) {
           JOptionPane.showMessageDialog(null, "Validade Inválida", "Mensage Error", JOptionPane.ERROR_MESSAGE);
            //retorna falso
            return false;
        }
    }
    
    public static boolean dataMenorQueHoje(String data) {
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       
        LocalDate dataVerificada = LocalDate.parse(data, dtf);
        //Esse comando pega a data de hoje
        LocalDate hoje = LocalDate.now();
       
        
        return dataVerificada.compareTo(hoje) <= 0;
        
        
    }
}
