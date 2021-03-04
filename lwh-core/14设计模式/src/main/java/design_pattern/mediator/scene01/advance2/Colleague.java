package design_pattern.mediator.scene01.advance2;
/*同事类的基类*/
public abstract class Colleague {

	protected Mediator mediator;
	public Colleague(Mediator _mediator){
		this.mediator =_mediator;
	}
}

