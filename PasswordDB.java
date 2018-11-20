import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class PasswordDB
{
    ArrayList<User> users=new ArrayList<>();
    public boolean addUser(String name, String userName, String email, String password){
        boolean entered=false;
        try{
            checkUserName(userName);
            User u=new User(name, userName, email,password);
            users.add(u);
            entered=true;
        }catch(NotUniqueException e){
            System.out.println(e);
        }catch(PasswordException e){
            System.out.println(e);
        }
        return entered;
    }

    public void menu(){
        Scanner sc=new Scanner(System.in);
        int i=0;
        while(i!=-1){
            System.out.println("-------Menu---------");

            System.out.println("1=Load Database");
            System.out.println("2=Save Database");
            System.out.println("3=Search Database");
            System.out.println("4=Update Database");
            System.out.println("5=close");
            int in=sc.nextInt();
            switch(in){
                case 1:
                load();
                break;
                case 2:
                save();
                break;
                case 3:
                search();
                break;
                case 4:
                update();
                break;
                case 5:
                i=-1;
                break;
            }
        }
    }

    public void checkUserName(String userName) throws NotUniqueException{
        boolean unique=true;
        for(User u:users){
            if(u.getUserName().equals(userName)){
                unique=false;
            }
        }
        if(!unique){
            throw new NotUniqueException();
        }
    }

    public void update(){
        Scanner sc =new Scanner(System.in);
        boolean entered=false;
        while(!entered){
            System.out.println("To enter a user press 1 to quit press 0");
            if(sc.nextInt()==0){
                entered=true;
            }else{
                System.out.println("Enter a name");
                String name=sc.next();
                System.out.println("Enter a user name");
                String userName=sc.next();
                System.out.println("Enter a password");
                String password=sc.next();
                System.out.println("Enter a email");
                String email=sc.next();
                entered=addUser(name,userName,email,password);
                if(!entered){
                    System.out.println("didnt work");
                }else{
                    System.out.println("worked");
                }
            }
        }
    }

    public void search(){
        Scanner scan=new Scanner(System.in);
        System.out.println("to search on name press 1, to search on user name press 2");
        int n=scan.nextInt();
        boolean found=false;
        if(n==1){
            System.out.println("Enter name");
            String name=scan.next();
            for(User u:users){
                if(u.getName().equals(name)){
                    System.out.println("User exists");
                    System.out.println(u);
                    found=true;
                }
            }
        }else if(n==2){
            System.out.println("Enter user name");
            String name=scan.next();
            for(User u:users){
                if(u.getUserName().equals(name)){
                    System.out.println("User exists");
                    System.out.println(u);
                    found=true;
                }
            }
        }
        if(!found){
            System.out.println("User not found");
        }
    }

    public void save(){
        try{
            FileWriter writer=new FileWriter("J:\\IP\\PassWordsDatabase.txt");
            for(User u: users){
                writer.write(u.getName()+" "+u.getUserName()+" "+u.getEmail()+" "+u.getPassword());
                writer.write(System.lineSeparator());
            }
            writer.close();
        }catch(FileNotFoundException e){
            System.out.println("file not found");

        }catch(IOException e){
            System.out.println("couldn't save");
        }
    }

    public void load(){
        try{
            Scanner sc=new Scanner(new FileReader("J:\\IP\\PassWordsDatabase.txt"));
            while(sc.hasNextLine()){
                
                User u=new User(sc.next(),sc.next(),sc.next(),sc.next());
                users.add(u);
            }
        }catch(PasswordException e){

            System.out.println(e);
        }catch(IOException e){

        }
    }

}
