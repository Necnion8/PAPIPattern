package com.gmail.necnionch.myplugin.papipattern.bukkit.pattern;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class HolderPattern {

    private final Pattern name;
    private final List<MatchEntry> matches;

    public HolderPattern(Pattern name, List<MatchEntry> matches) {
        this.name = name;
        this.matches = matches;
    }

    public Pattern getName() {
        return name;
    }

    public List<MatchEntry> getMatches() {
        return matches;
    }

    public @Nullable HolderPatternMatcher find(String input) {
        Matcher m = name.matcher(input);
        return m.matches() ? new HolderPatternMatcher(this, m) : null;
    }

    public static @Nullable HolderPattern deserialize(String nameRaw, List<Map<?, ?>> value) {
        Pattern name;
        try {
            name = Pattern.compile(nameRaw);
        } catch (PatternSyntaxException e) {
            e.printStackTrace();
            return null;
        }

        List<MatchEntry> matches = Lists.newArrayList();

        for (Map<?, ?> data : value) {
            MatchEntry match;
            try {
                match = MatchEntry.deserialize(data);
            } catch (PatternSyntaxException e) {
                e.printStackTrace();
                continue;
            }
            if (match != null)
                matches.add(match);
        }

        return new HolderPattern(name, matches);
    }


    public static class MatchEntry {

        private final @Nullable String input;
        private final @Nullable Pattern check;
        private final String output;

        public MatchEntry(String output, @Nullable Pattern check, @Nullable String input) {
            this.output = output;
            this.check = check;
            this.input = input;
        }

        public String getOutput() {
            return output;
        }

        public @Nullable Pattern getCheck() {
            return check;
        }

        public @Nullable String getInput() {
            return input;
        }


        public static @Nullable MatchEntry deserialize(Map<?, ?> data) throws PatternSyntaxException {
            Pattern check;
            String input, output;
            if (data.get("output") instanceof String) {
                output = (String) data.get("output");
            } else {
                return null;
            }

            if (data.get("check") instanceof String) {
                String raw = (String) data.get("check");
                check = Pattern.compile(raw);
            } else {
                check = null;
            }

            if (data.get("input") instanceof String) {
                input = ((String) data.get("input"));
            } else {
                input = null;
            }

            return new MatchEntry(output, check, input);
        }

    }

}
