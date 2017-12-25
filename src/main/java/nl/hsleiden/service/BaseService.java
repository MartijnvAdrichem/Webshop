package nl.hsleiden.service;

import nl.hsleiden.model.Account;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotFoundException;

/**
 *
 * @author Peter van Vliet
 * @param <T>
 */
public class BaseService<T>
{
    public T requireResult(T model)
    {
        if (model == null)
        {
            throw new NotFoundException();
        }
        
        return model;
    }
    
    public void assertSelf(Account user1, Account user2)
    {
        if (!user1.equals(user2))
        {
            throw new ForbiddenException();
        }
    }
}
