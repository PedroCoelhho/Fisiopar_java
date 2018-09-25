/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engine;

import javax.swing.JInternalFrame;

/**
 *
 * @author Juan
 */
public class Group {
    private String nomeGrupo;
    private JInternalFrame[] telas;

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public JInternalFrame[] getTelas() {
        return telas;
    }

    public void setTelas(JInternalFrame[] telas) {
        this.telas = telas;
    }
    
    public Group(String nomeGrupo, JInternalFrame[] telas) {
        this.nomeGrupo = nomeGrupo;
        this.telas = telas;
    }
    
}
