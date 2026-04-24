package az.nexus.subscription_api_project.exception;

public class AlreadyExistsException extends RuntimeException
{
    public AlreadyExistsException(String message)
    {
        super(message);
    }
}
