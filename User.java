
public class User
{
    private String name;
    private String userName;
    private String email;
    private String password;
    public User(String name, String userName, String email, String password)throws PasswordException{
        this.name=name;
        this.userName=userName;
        this.email=email;
        if(password.length()<8){
            throw new PasswordException();
        }else{
            this.password=password;
        }
    }
    public String getUserName(){
        return userName;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
        
    }
    public String getPassword(){
        return password;
    }
    public void toPrint(){
        System.out.println("Name:"+name);
        System.out.println("User Name:"+userName);
        System.out.println("Email:"+email);
        
    }
}
