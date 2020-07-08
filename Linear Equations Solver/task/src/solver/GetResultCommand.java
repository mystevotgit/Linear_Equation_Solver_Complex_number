package solver;

public class GetResultCommand implements Command {
    LinearEquation equation;
    GetResultCommand(LinearEquation equation) {
        this.equation = equation;
    }

    @Override
    public void execute() {
        equation.resolveEquation();
    }
}
