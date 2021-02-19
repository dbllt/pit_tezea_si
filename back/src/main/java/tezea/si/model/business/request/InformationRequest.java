package tezea.si.model.business.request;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("Information")
public class InformationRequest extends Request{
}
