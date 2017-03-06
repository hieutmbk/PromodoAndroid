package techkids.vn.android7pomodoro.events;

/**
 * Created by minhh on 04/03/2017.
 */



public class TimerCommandEvent {
    private TimerCommand command;

    public TimerCommandEvent(TimerCommand command) {
        this.command = command;
    }

    public TimerCommand getCommand() {
        return command;
    }

    public void setCommand(TimerCommand command) {
        this.command = command;
    }
}
