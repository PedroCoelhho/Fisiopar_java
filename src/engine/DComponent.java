package engine;

/**
 * Classe responsável pela estrutura dos ícones arrastáveis
 * @author Juan Galvão
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class DComponent extends JLabel {
    // Variáveis de cordenada
    private volatile int screenX = 0;
    private volatile int screenY = 0;
    private volatile int myX = 0;
    private volatile int myY = 0;
    
    /**
     * Construtor do Componente Arrastável
     * @param x Cordenada de inicialização X em relação a tela
     * @param y Cordenada de inicialização Y em relação a tela
     * @param tela JInternalFrame que será aberto ao clicar 2x
     */
    public DComponent(int x, int y, JInternalFrame tela) {
        // Definições padrão
        setBorder(new LineBorder(Color.BLACK, 0));
        setBackground(Color.BLUE);
        setBounds(x, y, 68, 68);
        setOpaque(false);
        setIcon(tela.getFrameIcon());
        setToolTipText(tela.getToolTipText());
        setText(tela.getName());
        setForeground(Color.WHITE);
        setHorizontalTextPosition(JLabel.CENTER);
        setVerticalTextPosition(JLabel.BOTTOM);
        
        // Ao clicar duas vezes...
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && !e.isConsumed()) {
                    try {
                        e.consume();
                        // Adicionar tela ao Container Parent
                        try {
                            getParent().add(tela);
                        } catch(Exception q) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Não é possível abrir duas janelas iguais!\n" + q
                            );
                        }

                        try {
                            tela.setSelected(true);
                        } catch (java.beans.PropertyVetoException ex) { }
                        getParent().getComponent(getParent().getComponentCount() - 1).setVisible(true);
                        //Yagami.createFrame(tela);
                    } catch (HeadlessException error) {
                        JOptionPane.showMessageDialog(null, error);
                    }
                    
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                screenX = e.getXOnScreen();
                screenY = e.getYOnScreen();

                myX = getX();
                myY = getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

        });
        addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent e) {
                int deltaX = e.getXOnScreen() - screenX;
                int deltaY = e.getYOnScreen() - screenY;

                setLocation(myX + deltaX, myY + deltaY);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }

        });
    }
    

}
