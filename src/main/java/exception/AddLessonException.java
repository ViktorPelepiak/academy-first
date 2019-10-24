package exception;

public class AddLessonException extends Exception{
    public AddLessonException (){
        super();
    }

    public AddLessonException (String message){
        super(message);
    }
}
