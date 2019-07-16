package interviewquestion.juc;

import jdk.nashorn.internal.objects.annotations.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author wcg
 * @CreateDate 2019/7/5-17:25
 */


class User {
    String username;
    int age;

    public User() {
    }

    public User(String username, int age) {

        this.username = username;
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public int getAge() {
        return age;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}

public class AtomicReferenceDemo {
    public static void main(String[] args) {

        User z3 = new User("z3",22);
        User l4 = new User("l4",25);
        AtomicReference<User> atomicReference = new AtomicReference<>();
        atomicReference.set(z3);
        System.out.println(atomicReference
                .compareAndSet(z3, l4) +" \t " +atomicReference.get().toString());
        System.out.println(atomicReference
                .compareAndSet(z3, l4) +" \t " +atomicReference.get().toString());


    }

}
