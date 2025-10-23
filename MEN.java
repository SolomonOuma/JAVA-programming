public class MEN {
    void MEAN_GRADE(double OOP, double OOSDM, double DBMS){
        System.out.printf("Student's 1 Mean Grade:%.3f \n",((OOP+OOSDM+DBMS)/3));
    }
    void MEAN_GRADE(int OOP, int OOSDM){
        System.out.printf("Student's 2 Mean Grade:%d \n", ((OOP+OOSDM)/2));
    }
    public static void main(String[] args){
        MEN stud = new MEN();
        stud.MEAN_GRADE(55, 69, 81);
        stud.MEAN_GRADE(64, 72);
    }
}
