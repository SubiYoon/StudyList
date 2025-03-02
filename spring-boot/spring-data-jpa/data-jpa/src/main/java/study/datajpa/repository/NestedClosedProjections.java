package study.datajpa.repository;

public interface NestedClosedProjections {

    String getUsername();
    TeamInfo getTeam();
    String getTeamName();

    interface TeamInfo {
        String getName();
    }
}
