package fr.insa.fmc.javaback.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CasierDisponibilite {

    @JsonFormat(pattern = "yyyy-MM-ddTHH:mm:ss.SSSZ")
    private Date date;

    private enum enumEtatCasier{occupe,libre}

}
