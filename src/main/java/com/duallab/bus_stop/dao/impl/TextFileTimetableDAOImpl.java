package com.duallab.bus_stop.dao.impl;

import com.duallab.bus_stop.dao.TimetableDAO;
import com.duallab.bus_stop.dao.impl.parser.TimetableEntryParser;
import com.duallab.bus_stop.domain.Timetable;
import com.duallab.bus_stop.exception.TextFileTimetableDAOException;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class TextFileTimetableDAOImpl implements TimetableDAO {

    private final FileInputStream fileInputStream;
    private Timetable cache;

    public TextFileTimetableDAOImpl(String filePath){
        try {
            this.fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new TextFileTimetableDAOException("file not found");
        }
    }

    @Override
    public Timetable getTimeTable() {
        if(this.cache != null) {
            return this.cache;
        }

        List<String> fileLines;

        try {
            fileLines = getFileLines();
        } catch (IOException e) {
            throw new TextFileTimetableDAOException(e);
        }

        Timetable timetable = new Timetable();
        timetable.setEntries(
                fileLines.stream().map(TimetableEntryParser::parse).collect(Collectors.toList())
        );

        this.cache = timetable;

        return timetable;
    }

    private List<String> getFileLines() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        List<String> lines = bufferedReader.lines().collect(Collectors.toList());

        fileInputStream.getChannel().position(0);

        return lines;
    }
}
