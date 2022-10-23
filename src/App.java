import java.util.*;
public class App {

    public static void main(String[] args) {
        UserSystem us= new UserSystem();
        AdminSystem as= new AdminSystem();
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while(choice < 3)
        {
            System.out.println("Please select user mode: \n1: User\n2: Admin\n3: Exit App");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                    us.viewScreen();
                    break;
                case 2:
                    as.viewScreen();
                    break;
                default:
                    break;
            }
        }
        System.out.println("Thank you for using MOBLIMA");
        sc.close();
    }
}