package logic;

import facade.UtilisateursFacade;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.Utilisateurs;

/**
 *
 * @author valibert
 */
@Named(value = "utilisateursManageur")
@SessionScoped
public class UtilisateursManageur implements Serializable {
    
    private String mailUtil;
    private String mdpUtil;
    private Utilisateurs currentUser;
    private ArrayList<Utilisateurs> listUser;
    
    @EJB
            UtilisateursFacade utilisateursFacade;
    
    /**
     * Creates a new instance of UtilisateursManageur
     */
    public UtilisateursManageur() {
    }
    
    public String getMailUtil() {
        return mailUtil;
    }
    
    public void setMailUtil(String mailUtil) {
        this.mailUtil = mailUtil;
    }
    
    public String getMdpUtil() {
        return mdpUtil;
    }
    
    public void setMdpUtil(String mdpUtil) {
        this.mdpUtil = mdpUtil;
    }
    
    public Utilisateurs getCurrentUser() {
        return currentUser;
    }
    
    public void setCurrentUser(Utilisateurs currentUser) {
        this.currentUser = currentUser;
    }
    
    public UtilisateursFacade getUtilisateursFacade() {
        return utilisateursFacade;
    }
    
    public void setUtilisateursFacade(UtilisateursFacade utilisateursFacade) {
        this.utilisateursFacade = utilisateursFacade;
    }
    
    public ArrayList<Utilisateurs> getListUser() {
        return listUser;
    }
    
    public void setListUser(ArrayList<Utilisateurs> listUser) {
        this.listUser = listUser;
    }
    public String logout() throws IOException {
        
        /* Récupération et destruction de la session en cours */
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        session.invalidate();
        return "toIndexFromAll";
    }
    
    public String login() throws NoSuchAlgorithmException, NoSuchProviderException{
        // byte[] salt = getSalt();
        currentUser = utilisateursFacade.findOne(mailUtil, get_SHA_256_SecurePassword(mdpUtil));
        
        if (currentUser.getNomUtil() != null ) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
            session.setAttribute("currentUserId", currentUser.getIdUtil());
            session.setAttribute("currentUserNom", currentUser.getNomUtil());
            return "toCompteFromLogin";
        }
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Impossible de se connecter. Veuillez vérifier votre identifiant ou votre mot de passe");
        context.addMessage("formConnection", message);
        return "";
    }
    
    private static String get_SHA_256_SecurePassword(String passwordToHash)
    {
        String generatedPassword = null;
        String salt = "5f8f041b75042e56";
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // md.update(salt);
            byte[] bytes = md.digest((salt+passwordToHash).getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    
    @PostConstruct
    public void initUtilisateurs(){
        this.listUser = new ArrayList();
        this.listUser.addAll(utilisateursFacade.findAll());
    }
}
