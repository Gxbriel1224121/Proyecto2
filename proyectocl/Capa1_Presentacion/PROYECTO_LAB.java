package proyecto.Capa1_Presentacion;

import com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatMaterialLighterIJTheme;


public class PROYECTO_LAB {


    public static void main(String[] args) {
        FlatMaterialLighterIJTheme.setup();
        Login vm= new Login();
        vm.setVisible(true);
    } 
    
}
