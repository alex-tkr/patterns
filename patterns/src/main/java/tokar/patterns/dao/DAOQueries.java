package tokar.patterns.dao;

public class DAOQueries {
    public static String getComputers = "SELECT computer.idcomputer, computer.model, computer.idprocessor, computer.iddisplay,\n" +
            "processor.idprocessor, processor.model, processor.cores , processor.frequency,\n" +
            "display.iddisplay, display.refresh_rate, display.matrix_type\n" +
            "FROM computer\n" +
            "INNER JOIN processor ON computer.idprocessor = processor.idprocessor \n" +
            "INNER JOIN display ON computer.iddisplay = display.iddisplay;";
    public static String deleteComputerById = "delete from computer where idComputer =?;";
    public static String addComputer = "insert into computer(model, idProcessor, idDisplay) values(?, ?, ?);";
    public static String updateComputer = "update computer set model =?, idProcessor=?, idDisplay =? where idComputer =?;";
    public static String getComputerByModel = "SELECT computer.idcomputer idcomputer, computer.model, computer.idprocessor, computer.iddisplay,\n" +
            "processor.idprocessor idprocessor, processor.model, processor.cores , processor.frequency,\n" +
            "display.iddisplay iddisplay, display.refresh_rate, display.matrix_type\n" +
            "FROM computer\n" +
            "INNER JOIN processor ON computer.idprocessor = processor.idprocessor \n" +
            "INNER JOIN display ON computer.iddisplay = display.iddisplay where computer.model = ?;";
    public static String getComputerById = "SELECT computer.idcomputer idcomputer, computer.model, computer.idprocessor, computer.iddisplay,\n" +
            "processor.idprocessor idprocessor, processor.model, processor.cores , processor.frequency,\n" +
            "display.iddisplay iddisplay, display.refresh_rate, display.matrix_type\n" +
            "FROM computer\n" +
            "INNER JOIN processor ON computer.idprocessor = processor.idprocessor \n" +
            "INNER JOIN display ON computer.iddisplay = display.iddisplay where computer.idcomputer = ?;";
    public static String addDisplay = "insert into display(refresh_rate, matrix_type) values(?, ?);";
    public static String getDisplays = "SELECT display.iddisplay iddisplay, display.refresh_rate, display.matrix_type FROM display;";
    public static String addProcessor = "insert into processor(model, cores, frequency) values(?, ?, ?);";
    public static String getProcessors = "SELECT processor.idprocessor idprocessor, processor.model, processor.cores , processor.frequency FROM processor;";
    public static String insertObserver = "insert into observers(model, idProcessor, idDisplay) values(?, ?, ?);";
    public static String getObservers = "SELECT observers.idcomputer, observers.model, observers.idprocessor, observers.iddisplay,\n" +
            "processor.idprocessor, processor.model, processor.cores , processor.frequency,\n" +
            "display.iddisplay, display.refresh_rate, display.matrix_type\n" +
            "FROM observers\n" +
            "INNER JOIN processor ON observers.idprocessor = processor.idprocessor \n" +
            "INNER JOIN display ON observers.iddisplay = display.iddisplay;";
    static public String getRoles = "SELECT * FROM `roles`;";
    static public String signUpUser = """
            INSERT INTO `users` (username, idRole)
            VALUES (?, (SELECT `idRole` FROM `roles` WHERE
            `rolename` = ?));
            """;
    static public String signInUser = """
            SELECT `users`.idUser idUser, users.username, users.idRole, roles.idRole idRole, roles.rolename FROM `users`\s
            INNER JOIN `roles`\s
            ON `users`.`idRole` = `roles`.`idRole`
            WHERE `users`.`username` = ?;
            """;
}