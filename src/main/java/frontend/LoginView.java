package frontend;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;

import p2.launchpool.Usuario;
import p2.launchpool.Usuarioscontroller;









@Route(value = "login",layout=MainView.class)
public class LoginView extends VerticalLayout{

	
	public LoginView() throws Exception {
		Usuarioscontroller usuarios=Usuarioscontroller.controller();
		
		FormLayout columnLayout = new FormLayout();
		
		

		
		
		TextField regname = new TextField();
		regname.setPlaceholder("Nombre de usuario:");
		PasswordField  regpassword = new PasswordField ();
		regpassword.setPlaceholder("Contraseña:");

		
		
        Button regis = new Button("Registrarse",
                event ->{
                	
                	try {
						if(usuarios.buscar(regname.getValue())==null) {
							if(regpassword.getValue().length()>7) {
								try {
									usuarios.crear(regname.getValue(), regpassword.getValue());
									Notification.show("Usuario creado con exito");
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else {
								Notification.show("La contraseña ha de tener al menos 8 carácteres");
								
							}
							
						}else {
							Notification.show("Ya existe una cuenta con este nombre de usuario");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	
                });
        
        Button login = new Button("Iniciar sesión",
                event ->{
                	Usuario temp=null;
                	
                	try {
						temp=usuarios.buscar(regname.getValue());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                	
                	if(temp!=null) {
                		if(regpassword.getValue().equals(temp.getPwd())) {
                	
		                	
		                		
								VaadinSession.getCurrent().setAttribute("user", temp);
								
								
								Usuario current=(Usuario) VaadinSession.getCurrent().getAttribute("user");
								Notification.show("Bienvenido, "+ ((Usuario) VaadinSession.getCurrent().getAttribute("user")).getUsername()  );
                		}else {
                			Notification.show("La contraseña no coincide");
                		}
								
						    	
							
                	}else {
                		Notification.show("El usuario "+regname.getValue()+" no existe.");
                	}
                	
                	
                });
        
        columnLayout.add(regname,regpassword,regis,login);
        add(columnLayout);
		
		
	}
	
}
