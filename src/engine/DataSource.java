package engine;

import view.telas.Base;
import view.telas.CadUsuario;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import view.telas.*;

/**
 * Fonte de dados de objetos comuns para o Sistema, como necessariamente para os
 * ícones do DesktopPane. Aqui são armezandos em arrays os ícones, as janelas e
 * os DComponents. Sempre que for adicionar uma nova tela para o sistema, ela
 * deverá ser instânciada e armazenada nessa classe.
 *
 * @author Juan Galvão
 */
public class DataSource {

    /**
     * Instância global para acesso aos atributos da classe
     */
    public static final DataSource DS = new DataSource();

    
    public Group[] grupos = {
        new Group("Administração", new JInternalFrame[]{
            new Base(),
            new CadUsuario()
        }),
        new Group("Paciente", new JInternalFrame[]{
            new ViewPaciente(),
            new CadPaciente(),
            new MenuAtendimento()
            
            
        }),
           
        
    };
    /*
    public Grupo[] grupos = {
        new Grupo("Administração", new JInternalFrame[]{
            new Base(),
            new CadUsuario()
        }),
        new Grupo("Paciente", new JInternalFrame[]{
            new ViewPaciente(),
            new CadPaciente(),
            new CadAtendimento()
        })
    };
    */
    
    
    /**
     * Array armazenando as telas.
     * Usabilidade: Abrir por name
     */
    public JInternalFrame[] telas = {
        new CadAluno(),
        new CadFisioterapeuta(),
        new CadFuncionario(),
        new CadPaciente(),
        new CadUsuario(),
        new ViewPaciente(),
        new MenuAtendimento(),
        new FiltrarCid10()
        
        
    };

    /**
     * Array armazenando os ícones.
     * @deprecated Uso considerávelmente desnecessário.
     */
    public Icon[] icones = {
        new ImageIcon(".\\img\\iconAgenda.png"),
        new ImageIcon(".\\img\\iconPacientes.png"),
        new ImageIcon(".\\img\\iconBase.png"),
        new ImageIcon(".\\img\\iconCid10.png"),
        
  
    };

    /**
     * Array armazenando os componentes que abrem a tela
     *
     * Ex. new DComponent(x, y, tela, icone) X e Y: Coordenadas aonde os
     * componentes serão colocados na tela Tela: Index da array telas[]
     * escolhendo a tela desejada Icone: Index da array icones[] escolhendo o
     * icone desejado
     *
     * Ex². new DComponent(10, 98, telas[3], icones[2]
     *
     * IMPORTANTE: O X basicamente se refere a coluna, então, usar no máximo 8
     * componentes na mesma coluna, depois que chegar ao limite, aonde o Y ja
     * deve estar em 578, aumentar o X em 68 e começar uma nova contagem para o
     * Y.
     *
     * Ex³. ... new DComponent(10, 498, tela, icone) new DComponent(10, 578,
     * tela, icone) new DComponent(78, 10, tela, icone) new DComponent(78, 98,
     * tela, icone) new DComponent(78, 178, tela, icone) ...
     *
     * Fazendo isso, uma nova coluna será iniciadaa ao lado da anterior.
     */
    
    /*public DComponent[] atalhos = {
        new DComponent(10, 18, telas[0], icones[0]),
        new DComponent(10, 98, telas[1], icones[1]),
        new DComponent(10, 178, telas[2], icones[2])
    };*/
    
    public DComponent[] admin = {
        new DComponent(10, 18, new ViewPaciente()),
        new DComponent(10, 98, new CadUsuario()),
        new DComponent(10, 178, new MenuAtendimento()),
        new DComponent(10, 258, new CadPaciente()),
        new DComponent(10, 338, new CadAluno()),
        new DComponent(10, 418, new CadFisioterapeuta()),
        new DComponent(10, 498, new CadFuncionario()),
        new DComponent(10, 578, new FiltrarCid10())
        
    };
    
    public DComponent[] fisioterapeuta = {
        new DComponent(10, 18, telas[0]),
        new DComponent(10, 98, telas[1])
    };
    
    public DComponent[] funcionario = {
        new DComponent(10, 18, telas[1])
    };
    
    public DComponent[] aluno = {
        new DComponent(10, 18, telas[3]),
        new DComponent(10, 98, telas[0]),
        new DComponent(10, 178, telas[1])
        
       

    };
}
