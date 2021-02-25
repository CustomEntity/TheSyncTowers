package fr.customentity.thesynctowers.hook;

public interface Hook {

    boolean onSetup();

    String getHookName();

    boolean isEnabled();

    void setEnabled(boolean enabled);
}
