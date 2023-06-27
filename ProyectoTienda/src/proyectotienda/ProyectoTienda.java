package proyectotienda;

import com.alvarobajo.views.FrmLogin;
import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;

/**
 *
 * @author alvaroBajo
 */
public class ProyectoTienda {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FlatMaterialLighterIJTheme.setup();
        FrmLogin loginn = new FrmLogin();
        loginn.setVisible(true);


    }

}
