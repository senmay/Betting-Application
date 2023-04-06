package com.dominik.typer.events;

import com.dominik.typer.model.MatchResult;
import lombok.Getter;
import lombok.experimental.Delegate;
import org.springframework.context.ApplicationEvent;

public class MatchResultEvent extends ApplicationEvent {

    @Getter
    @Delegate
    private final MatchResult matchResult;

    public MatchResultEvent(Object source, MatchResult matchResult) {
        super(source);
        this.matchResult = matchResult;
    }
}
