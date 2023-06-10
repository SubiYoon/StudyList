package base;/*
 * Copyright 2002-2023 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;


/**
 * Miscellaneous {@link String} utility methods.
 *
 * <p>Mainly for internal use within the framework; consider
 * <a href="https://commons.apache.org/proper/commons-lang/">Apache's Commons Lang</a>
 * for a more comprehensive suite of {@code String} utilities.
 *
 * <p>This class delivers some simple functionality that should really be
 * provided by the core Java {@link String} and {@link StringBuilder}
 * classes. It also provides easy-to-use methods to convert between
 * delimited strings, such as CSV strings, and collections and arrays.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Keith Donald
 * @author Rob Harrop
 * @author Rick Evans
 * @author Arjen Poutsma
 * @author Sam Brannen
 * @author Brian Clozel
 * @since 16 April 2001
 */
public abstract class StringUtils {

    private static final String[] EMPTY_STRING_ARRAY = {};

    private static final String FOLDER_SEPARATOR = "/";

    private static final char FOLDER_SEPARATOR_CHAR = '/';

    private static final String WINDOWS_FOLDER_SEPARATOR = "\\";

    private static final String TOP_PATH = "..";

    private static final String CURRENT_PATH = ".";

    private static final char EXTENSION_SEPARATOR = '.';

    private static final int DEFAULT_TRUNCATION_THRESHOLD = 100;

    private static final String TRUNCATION_SUFFIX = " (truncated)...";


    //---------------------------------------------------------------------
    // General convenience methods for working with Strings
    //---------------------------------------------------------------------

    /**
     * Check whether the given object (possibly a {@code String}) is empty.
     * This is effectively a shortcut for {@code !hasLength(String)}.
     * <p>This method accepts any Object as an argument, comparing it to
     * {@code null} and the empty String. As a consequence, this method
     * will never return {@code true} for a non-null non-String object.
     * <p>The Object signature is useful for general attribute handling code
     * that commonly deals with Strings but generally has to iterate over
     * Objects since attributes may e.g. be primitive value objects as well.
     * <p><b>Note: If the object is typed to {@code String} upfront, prefer
     * {@link #hasLength(String)} or {@link #hasText(String)} instead.</b>
     * @param str the candidate object (possibly a {@code String})
     * @since 3.2.1
     * @deprecated as of 5.3, in favor of {@link #hasLength(String)} and
     * {@link #hasText(String)} (or {@link ObjectUtils#isEmpty(Object)})
     */
    @Deprecated
    public static boolean isEmpty( Object str) {
        return (str == null || "".equals(str));
    }

    /**
     * Check that the given {@code CharSequence} is neither {@code null} nor
     * of length 0.
     * <p>Note: this method returns {@code true} for a {@code CharSequence}
     * that purely consists of whitespace.
     * <p><pre class="code">
     * base.StringUtils.hasLength(null) = false
     * base.StringUtils.hasLength("") = false
     * base.StringUtils.hasLength(" ") = true
     * base.StringUtils.hasLength("Hello") = true
     * </pre>
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
     * @see #hasLength(String)
     * @see #hasText(CharSequence)
     */
    public static boolean hasLength( CharSequence str) {
        return (str != null && str.length() > 0);
    }

    /**
     * Check that the given {@code String} is neither {@code null} nor of length 0.
     * <p>Note: this method returns {@code true} for a {@code String} that
     * purely consists of whitespace.
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null} and has length
     * @see #hasLength(CharSequence)
     * @see #hasText(String)
     */
    public static boolean hasLength( String str) {
        return (str != null && !str.isEmpty());
    }

    /**
     * Check whether the given {@code CharSequence} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code CharSequence} is not {@code null}, its length is greater than
     * 0, and it contains at least one non-whitespace character.
     * <p><pre class="code">
     * base.StringUtils.hasText(null) = false
     * base.StringUtils.hasText("") = false
     * base.StringUtils.hasText(" ") = false
     * base.StringUtils.hasText("12345") = true
     * base.StringUtils.hasText(" 12345 ") = true
     * </pre>
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not {@code null},
     * its length is greater than 0, and it does not contain whitespace only
     * @see #hasText(String)
     * @see #hasLength(CharSequence)
     * @see Character#isWhitespace
     */
    public static boolean hasText( CharSequence str) {
        return (str != null && str.length() > 0 && containsText(str));
    }

    /**
     * Check whether the given {@code String} contains actual <em>text</em>.
     * <p>More specifically, this method returns {@code true} if the
     * {@code String} is not {@code null}, its length is greater than 0,
     * and it contains at least one non-whitespace character.
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not {@code null}, its
     * length is greater than 0, and it does not contain whitespace only
     * @see #hasText(CharSequence)
     * @see #hasLength(String)
     * @see Character#isWhitespace
     */
    public static boolean hasText( String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given {@code CharSequence} contains any whitespace characters.
     * @param str the {@code CharSequence} to check (may be {@code null})
     * @return {@code true} if the {@code CharSequence} is not empty and
     * contains at least 1 whitespace character
     * @see Character#isWhitespace
     */
    public static boolean containsWhitespace( CharSequence str) {
        if (!hasLength(str)) {
            return false;
        }

        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check whether the given {@code String} contains any whitespace characters.
     * @param str the {@code String} to check (may be {@code null})
     * @return {@code true} if the {@code String} is not empty and
     * contains at least 1 whitespace character
     * @see #containsWhitespace(CharSequence)
     */
    public static boolean containsWhitespace( String str) {
        return containsWhitespace((CharSequence) str);
    }

    /**
     * Trim leading and trailing whitespace from the given {@code String}.
     * @param str the {@code String} to check
     * @return the trimmed {@code String}
     * @see Character#isWhitespace
     * @deprecated since 6.0, in favor of {@link String#strip()}
     */
    @Deprecated(since = "6.0")
    public static String trimWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }

        return str.strip();
    }

    /**
     * Trim <em>all</em> whitespace from the given {@code CharSequence}:
     * leading, trailing, and in between characters.
     * @param text the {@code CharSequence} to check
     * @return the trimmed {@code CharSequence}
     * @since 5.3.22
     * @see #trimAllWhitespace(String)
     * @see Character#isWhitespace
     */
    public static CharSequence trimAllWhitespace(CharSequence text) {
        if (!hasLength(text)) {
            return text;
        }

        int len = text.length();
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < len; i++) {
            char c = text.charAt(i);
            if (!Character.isWhitespace(c)) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * Trim <em>all</em> whitespace from the given {@code String}:
     * leading, trailing, and in between characters.
     * @param str the {@code String} to check
     * @return the trimmed {@code String}
     * @see #trimAllWhitespace(CharSequence)
     * @see Character#isWhitespace
     */
    public static String trimAllWhitespace(String str) {
        if (str == null) {
            return null;
        }
        return trimAllWhitespace((CharSequence) str).toString();
    }

    /**
     * Trim leading whitespace from the given {@code String}.
     * @param str the {@code String} to check
     * @return the trimmed {@code String}
     * @see Character#isWhitespace
     * @deprecated since 6.0, in favor of {@link String#stripLeading()}
     */
    @Deprecated(since = "6.0")
    public static String trimLeadingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }

        return str.stripLeading();
    }

    /**
     * Trim trailing whitespace from the given {@code String}.
     * @param str the {@code String} to check
     * @return the trimmed {@code String}
     * @see Character#isWhitespace
     * @deprecated since 6.0, in favor of {@link String#stripTrailing()}
     */
    @Deprecated(since = "6.0")
    public static String trimTrailingWhitespace(String str) {
        if (!hasLength(str)) {
            return str;
        }

        return str.stripTrailing();
    }

    /**
     * Trim all occurrences of the supplied leading character from the given {@code String}.
     * @param str the {@code String} to check
     * @param leadingCharacter the leading character to be trimmed
     * @return the trimmed {@code String}
     */
    public static String trimLeadingCharacter(String str, char leadingCharacter) {
        if (!hasLength(str)) {
            return str;
        }

        int beginIdx = 0;
        while (beginIdx < str.length() && leadingCharacter == str.charAt(beginIdx)) {
            beginIdx++;
        }
        return str.substring(beginIdx);
    }

    /**
     * Trim all occurrences of the supplied trailing character from the given {@code String}.
     * @param str the {@code String} to check
     * @param trailingCharacter the trailing character to be trimmed
     * @return the trimmed {@code String}
     */
    public static String trimTrailingCharacter(String str, char trailingCharacter) {
        if (!hasLength(str)) {
            return str;
        }

        int endIdx = str.length() - 1;
        while (endIdx >= 0 && trailingCharacter == str.charAt(endIdx)) {
            endIdx--;
        }
        return str.substring(0, endIdx + 1);
    }

    /**
     * base.Test if the given {@code String} matches the given single character.
     * @param str the {@code String} to check
     * @param singleCharacter the character to compare to
     * @since 5.2.9
     */
    public static boolean matchesCharacter( String str, char singleCharacter) {
        return (str != null && str.length() == 1 && str.charAt(0) == singleCharacter);
    }

    /**
     * base.Test if the given {@code String} starts with the specified prefix,
     * ignoring upper/lower case.
     * @param str the {@code String} to check
     * @param prefix the prefix to look for
     * @see String#startsWith
     */
    public static boolean startsWithIgnoreCase( String str,  String prefix) {
        return (str != null && prefix != null && str.length() >= prefix.length() &&
                str.regionMatches(true, 0, prefix, 0, prefix.length()));
    }

    /**
     * base.Test if the given {@code String} ends with the specified suffix,
     * ignoring upper/lower case.
     * @param str the {@code String} to check
     * @param suffix the suffix to look for
     * @see String#endsWith
     */
    public static boolean endsWithIgnoreCase( String str,  String suffix) {
        return (str != null && suffix != null && str.length() >= suffix.length() &&
                str.regionMatches(true, str.length() - suffix.length(), suffix, 0, suffix.length()));
    }

    /**
     * base.Test whether the given string matches the given substring
     * at the given index.
     * @param str the original string (or StringBuilder)
     * @param index the index in the original string to start matching against
     * @param substring the substring to match at the given index
     */
    public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
        if (index + substring.length() > str.length()) {
            return false;
        }
        for (int i = 0; i < substring.length(); i++) {
            if (str.charAt(index + i) != substring.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Count the occurrences of the substring {@code sub} in string {@code str}.
     * @param str string to search in
     * @param sub string to search for
     */
    public static int countOccurrencesOf(String str, String sub) {
        if (!hasLength(str) || !hasLength(sub)) {
            return 0;
        }

        int count = 0;
        int pos = 0;
        int idx;
        while ((idx = str.indexOf(sub, pos)) != -1) {
            ++count;
            pos = idx + sub.length();
        }
        return count;
    }

    /**
     * Replace all occurrences of a substring within a string with another string.
     * @param inString {@code String} to examine
     * @param oldPattern {@code String} to replace
     * @param newPattern {@code String} to insert
     * @return a {@code String} with the replacements
     */
    public static String replace(String inString, String oldPattern,  String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            // no occurrence -> can return input as-is
            return inString;
        }

        int capacity = inString.length();
        if (newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }
        StringBuilder sb = new StringBuilder(capacity);

        int pos = 0;  // our position in the old string
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString, pos, index);
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }

        // append any characters to the right of a match
        sb.append(inString, pos, inString.length());
        return sb.toString();
    }

    /**
     * Delete all occurrences of the given substring.
     * @param inString the original {@code String}
     * @param pattern the pattern to delete all occurrences of
     * @return the resulting {@code String}
     */
    public static String delete(String inString, String pattern) {
        return replace(inString, pattern, "");
    }

    /**
     * Delete any character in a given {@code String}.
     * @param inString the original {@code String}
     * @param charsToDelete a set of characters to delete.
     * E.g. "az\n" will delete 'a's, 'z's and new lines.
     * @return the resulting {@code String}
     */
    public static String deleteAny(String inString,  String charsToDelete) {
        if (!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }

        int lastCharIndex = 0;
        char[] result = new char[inString.length()];
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                result[lastCharIndex++] = c;
            }
        }
        if (lastCharIndex == inString.length()) {
            return inString;
        }
        return new String(result, 0, lastCharIndex);
    }

    //---------------------------------------------------------------------
    // Convenience methods for working with formatted Strings
    //---------------------------------------------------------------------

    /**
     * Quote the given {@code String} with single quotes.
     * @param str the input {@code String} (e.g. "myString")
     * @return the quoted {@code String} (e.g. "'myString'"),
     * or {@code null} if the input was {@code null}
     */

    public static String quote( String str) {
        return (str != null ? "'" + str + "'" : null);
    }

    /**
     * Turn the given Object into a {@code String} with single quotes
     * if it is a {@code String}; keeping the Object as-is else.
     * @param obj the input Object (e.g. "myString")
     * @return the quoted {@code String} (e.g. "'myString'"),
     * or the input object as-is if not a {@code String}
     */

    public static Object quoteIfString( Object obj) {
        return (obj instanceof String ? quote((String) obj) : obj);
    }

    /**
     * Unqualify a string qualified by a '.' dot character. For example,
     * "this.name.is.qualified", returns "qualified".
     * @param qualifiedName the qualified name
     */
    public static String unqualify(String qualifiedName) {
        return unqualify(qualifiedName, '.');
    }

    /**
     * Unqualify a string qualified by a separator character. For example,
     * "this:name:is:qualified" returns "qualified" if using a ':' separator.
     * @param qualifiedName the qualified name
     * @param separator the separator
     */
    public static String unqualify(String qualifiedName, char separator) {
        return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
    }

    /**
     * Capitalize a {@code String}, changing the first letter to
     * upper case as per {@link Character#toUpperCase(char)}.
     * No other letters are changed.
     * @param str the {@code String} to capitalize
     * @return the capitalized {@code String}
     */
    public static String capitalize(String str) {
        return changeFirstCharacterCase(str, true);
    }

    /**
     * Uncapitalize a {@code String}, changing the first letter to
     * lower case as per {@link Character#toLowerCase(char)}.
     * No other letters are changed.
     * @param str the {@code String} to uncapitalize
     * @return the uncapitalized {@code String}
     */
    public static String uncapitalize(String str) {
        return changeFirstCharacterCase(str, false);
    }

    /**
     * Uncapitalize a {@code String} in JavaBeans property format,
     * changing the first letter to lower case as per
     * {@link Character#toLowerCase(char)}, unless the initial two
     * letters are upper case in direct succession.
     * @param str the {@code String} to uncapitalize
     * @return the uncapitalized {@code String}
     * @since 6.0
     * @see java.beans.Introspector#decapitalize(String)
     */
    public static String uncapitalizeAsProperty(String str) {
        if (!hasLength(str) || (str.length() > 1 && Character.isUpperCase(str.charAt(0)) &&
                Character.isUpperCase(str.charAt(1)))) {
            return str;
        }
        return changeFirstCharacterCase(str, false);
    }

    private static String changeFirstCharacterCase(String str, boolean capitalize) {
        if (!hasLength(str)) {
            return str;
        }

        char baseChar = str.charAt(0);
        char updatedChar;
        if (capitalize) {
            updatedChar = Character.toUpperCase(baseChar);
        }
        else {
            updatedChar = Character.toLowerCase(baseChar);
        }
        if (baseChar == updatedChar) {
            return str;
        }

        char[] chars = str.toCharArray();
        chars[0] = updatedChar;
        return new String(chars);
    }

    /**
     * Extract the filename from the given Java resource path,
     * e.g. {@code "mypath/myfile.txt" &rarr; "myfile.txt"}.
     * @param path the file path (may be {@code null})
     * @return the extracted filename, or {@code null} if none
     */

    public static String getFilename( String path) {
        if (path == null) {
            return null;
        }

        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR_CHAR);
        return (separatorIndex != -1 ? path.substring(separatorIndex + 1) : path);
    }

    /**
     * Extract the filename extension from the given Java resource path,
     * e.g. "mypath/myfile.txt" &rarr; "txt".
     * @param path the file path (may be {@code null})
     * @return the extracted filename extension, or {@code null} if none
     */

    public static String getFilenameExtension( String path) {
        if (path == null) {
            return null;
        }

        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return null;
        }

        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR_CHAR);
        if (folderIndex > extIndex) {
            return null;
        }

        return path.substring(extIndex + 1);
    }

    /**
     * Strip the filename extension from the given Java resource path,
     * e.g. "mypath/myfile.txt" &rarr; "mypath/myfile".
     * @param path the file path
     * @return the path with stripped filename extension
     */
    public static String stripFilenameExtension(String path) {
        int extIndex = path.lastIndexOf(EXTENSION_SEPARATOR);
        if (extIndex == -1) {
            return path;
        }

        int folderIndex = path.lastIndexOf(FOLDER_SEPARATOR_CHAR);
        if (folderIndex > extIndex) {
            return path;
        }

        return path.substring(0, extIndex);
    }

    /**
     * Apply the given relative path to the given Java resource path,
     * assuming standard Java folder separation (i.e. "/" separators).
     * @param path the path to start from (usually a full file path)
     * @param relativePath the relative path to apply
     * (relative to the full file path above)
     * @return the full file path that results from applying the relative path
     */
    public static String applyRelativePath(String path, String relativePath) {
        int separatorIndex = path.lastIndexOf(FOLDER_SEPARATOR_CHAR);
        if (separatorIndex != -1) {
            String newPath = path.substring(0, separatorIndex);
            if (!relativePath.startsWith(FOLDER_SEPARATOR)) {
                newPath += FOLDER_SEPARATOR_CHAR;
            }
            return newPath + relativePath;
        }
        else {
            return relativePath;
        }
    }
    /**
     * Decode the given encoded URI component value. Based on the following rules:
     * <ul>
     * <li>Alphanumeric characters {@code "a"} through {@code "z"}, {@code "A"} through {@code "Z"},
     * and {@code "0"} through {@code "9"} stay the same.</li>
     * <li>Special characters {@code "-"}, {@code "_"}, {@code "."}, and {@code "*"} stay the same.</li>
     * <li>A sequence "{@code %<i>xy</i>}" is interpreted as a hexadecimal representation of the character.</li>
     * </ul>
     * @param source the encoded String
     * @param charset the character set
     * @return the decoded value
     * @throws IllegalArgumentException when the given source contains invalid encoded sequences
     * @since 5.0
     * @see java.net.URLDecoder#decode(String, String)
     */
    public static String uriDecode(String source, Charset charset) {
        int length = source.length();
        if (length == 0) {
            return source;
        }


        ByteArrayOutputStream baos = new ByteArrayOutputStream(length);
        boolean changed = false;
        for (int i = 0; i < length; i++) {
            int ch = source.charAt(i);
            if (ch == '%') {
                if (i + 2 < length) {
                    char hex1 = source.charAt(i + 1);
                    char hex2 = source.charAt(i + 2);
                    int u = Character.digit(hex1, 16);
                    int l = Character.digit(hex2, 16);
                    if (u == -1 || l == -1) {
                        throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
                    }
                    baos.write((char) ((u << 4) + l));
                    i += 2;
                    changed = true;
                }
                else {
                    throw new IllegalArgumentException("Invalid encoded sequence \"" + source.substring(i) + "\"");
                }
            }
            else {
                baos.write(ch);
            }
        }
        return (changed ? StreamUtils.copyToString(baos, charset) : source);
    }

    /**
     * Parse the given {@code String} value into a {@link Locale}, accepting
     * the {@link Locale#toString} format as well as BCP 47 language tags as
     * specified by {@link Locale#forLanguageTag}.
     * @param localeValue the locale value: following either {@code Locale's}
     * {@code toString()} format ("en", "en_UK", etc), also accepting spaces as
     * separators (as an alternative to underscores), or BCP 47 (e.g. "en-UK")
     * @return a corresponding {@code Locale} instance, or {@code null} if none
     * @throws IllegalArgumentException in case of an invalid locale specification
     * @since 5.0.4
     * @see #parseLocaleString
     * @see Locale#forLanguageTag
     */
    public static Locale parseLocale(String localeValue) {
        if (!localeValue.contains("_") && !localeValue.contains(" ")) {
            validateLocalePart(localeValue);
            Locale resolved = Locale.forLanguageTag(localeValue);
            if (resolved.getLanguage().length() > 0) {
                return resolved;
            }
        }
        return parseLocaleString(localeValue);
    }

    /**
     * Parse the given {@code String} representation into a {@link Locale}.
     * <p>For many parsing scenarios, this is an inverse operation of
     * {@link Locale#toString Locale's toString}, in a lenient sense.
     * This method does not aim for strict {@code Locale} design compliance;
     * it is rather specifically tailored for typical Spring parsing needs.
     * <p><b>Note: This delegate does not accept the BCP 47 language tag format.
     * Please use {@link #parseLocale} for lenient parsing of both formats.</b>
     * @param localeString the locale {@code String}: following {@code Locale's}
     * {@code toString()} format ("en", "en_UK", etc), also accepting spaces as
     * separators (as an alternative to underscores)
     * @return a corresponding {@code Locale} instance, or {@code null} if none
     * @throws IllegalArgumentException in case of an invalid locale specification
     */
    @SuppressWarnings("deprecation")  // for Locale constructors on JDK 19
    public static Locale parseLocaleString(String localeString) {
        if (localeString.equals("")) {
            return null;
        }
        String delimiter = "_";
        if (!localeString.contains("_") && localeString.contains(" ")) {
            delimiter = " ";
        }
        String[] tokens = localeString.split(delimiter, -1);
        if (tokens.length == 1) {
            String language = tokens[0];
            validateLocalePart(language);
            return new Locale(language);
        }
        else if (tokens.length == 2) {
            String language = tokens[0];
            validateLocalePart(language);
            String country = tokens[1];
            validateLocalePart(country);
            return new Locale(language, country);
        }
        else if (tokens.length > 2) {
            String language = tokens[0];
            validateLocalePart(language);
            String country = tokens[1];
            validateLocalePart(country);
            String variant = Arrays.stream(tokens).skip(2).collect(Collectors.joining(delimiter));
            return new Locale(language, country, variant);
        }
        throw new IllegalArgumentException("Invalid locale format: '" + localeString + "'");
    }

    private static void validateLocalePart(String localePart) {
        for (int i = 0; i < localePart.length(); i++) {
            char ch = localePart.charAt(i);
            if (ch != ' ' && ch != '_' && ch != '-' && ch != '#' && !Character.isLetterOrDigit(ch)) {
                throw new IllegalArgumentException(
                        "Locale part \"" + localePart + "\" contains invalid characters");
            }
        }
    }

    /**
     * Parse the given {@code timeZoneString} value into a {@link TimeZone}.
     * @param timeZoneString the time zone {@code String}, following {@link TimeZone#getTimeZone(String)}
     * but throwing {@link IllegalArgumentException} in case of an invalid time zone specification
     * @return a corresponding {@link TimeZone} instance
     * @throws IllegalArgumentException in case of an invalid time zone specification
     */
    public static TimeZone parseTimeZoneString(String timeZoneString) {
        TimeZone timeZone = TimeZone.getTimeZone(timeZoneString);
        if ("GMT".equals(timeZone.getID()) && !timeZoneString.startsWith("GMT")) {
            // We don't want that GMT fallback...
            throw new IllegalArgumentException("Invalid time zone specification '" + timeZoneString + "'");
        }
        return timeZone;
    }


    //---------------------------------------------------------------------
    // Convenience methods for working with String arrays
    //---------------------------------------------------------------------

    /**
     * Copy the given {@link Collection} into a {@code String} array.
     * <p>The {@code Collection} must contain {@code String} elements only.
     * @param collection the {@code Collection} to copy
     * (potentially {@code null} or empty)
     * @return the resulting {@code String} array
     */
    public static String[] toStringArray( Collection<String> collection) {
        return (collection!= null && !collection.isEmpty() ? collection.toArray(EMPTY_STRING_ARRAY) : EMPTY_STRING_ARRAY);
    }

    /**
     * Copy the given {@link Enumeration} into a {@code String} array.
     * <p>The {@code Enumeration} must contain {@code String} elements only.
     * @param enumeration the {@code Enumeration} to copy
     * (potentially {@code null} or empty)
     * @return the resulting {@code String} array
     */
    public static String[] toStringArray(Enumeration<String> enumeration) {
        return (enumeration != null ? toStringArray(Collections.list(enumeration)) : EMPTY_STRING_ARRAY);
    }

    /**
     * Append the given {@code String} to the given {@code String} array,
     * returning a new array consisting of the input array contents plus
     * the given {@code String}.
     * @param array the array to append to (can be {@code null})
     * @param str the {@code String} to append
     * @return the new array (never {@code null})
     */
    public static String[] addStringToArray( String[] array, String str) {
        if (array == null || array.length < 1) {
            return new String[] {str};
        }

        String[] newArr = new String[array.length + 1];
        System.arraycopy(array, 0, newArr, 0, array.length);
        newArr[array.length] = str;
        return newArr;
    }

    /**
     * Concatenate the given {@code String} arrays into one,
     * with overlapping array elements included twice.
     * <p>The order of elements in the original arrays is preserved.
     * @param array1 the first array (can be {@code null})
     * @param array2 the second array (can be {@code null})
     * @return the new array ({@code null} if both given arrays were {@code null})
     */
    public static String[] concatenateStringArrays( String[] array1,  String[] array2) {
        if (array1 == null || array1.length < 1) {
            return array2;
        }
        if (array2 == null || array2.length < 1) {
            return array1;
        }

        String[] newArr = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, newArr, 0, array1.length);
        System.arraycopy(array2, 0, newArr, array1.length, array2.length);
        return newArr;
    }

    /**
     * Sort the given {@code String} array if necessary.
     * @param array the original array (potentially empty)
     * @return the array in sorted form (never {@code null})
     */
    public static String[] sortStringArray(String[] array) {
        if (array == null || array.length < 1) {
            return array;
        }

        Arrays.sort(array);
        return array;
    }

    /**
     * Trim the elements of the given {@code String} array, calling
     * {@code String.trim()} on each non-null element.
     * @param array the original {@code String} array (potentially empty)
     * @return the resulting array (of the same size) with trimmed elements
     */
    public static String[] trimArrayElements(String[] array) {
        if (array == null || array.length < 1) {
            return array;
        }

        String[] result = new String[array.length];
        for (int i = 0; i < array.length; i++) {
            String element = array[i];
            result[i] = (element != null ? element.trim() : null);
        }
        return result;
    }

    /**
     * Remove duplicate strings from the given array.
     * <p>As of 4.2, it preserves the original order, as it uses a {@link LinkedHashSet}.
     * @param array the {@code String} array (potentially empty)
     * @return an array without duplicates, in natural sort order
     */
    public static String[] removeDuplicateStrings(String[] array) {
        if (array == null || array.length < 1) {
            return array;
        }

        Set<String> set = new LinkedHashSet<>(Arrays.asList(array));
        return toStringArray(set);
    }

    /**
     * Split a {@code String} at the first occurrence of the delimiter.
     * Does not include the delimiter in the result.
     * @param toSplit the string to split (potentially {@code null} or empty)
     * @param delimiter to split the string up with (potentially {@code null} or empty)
     * @return a two element array with index 0 being before the delimiter, and
     * index 1 being after the delimiter (neither element includes the delimiter);
     * or {@code null} if the delimiter wasn't found in the given input {@code String}
     */
    public static String[] split( String toSplit,  String delimiter) {
        if (!hasLength(toSplit) || !hasLength(delimiter)) {
            return null;
        }
        int offset = toSplit.indexOf(delimiter);
        if (offset < 0) {
            return null;
        }

        String beforeDelimiter = toSplit.substring(0, offset);
        String afterDelimiter = toSplit.substring(offset + delimiter.length());
        return new String[] {beforeDelimiter, afterDelimiter};
    }

    /**
     * Take an array of strings and split each element based on the given delimiter.
     * A {@code Properties} instance is then generated, with the left of the delimiter
     * providing the key, and the right of the delimiter providing the value.
     * <p>Will trim both the key and value before adding them to the {@code Properties}.
     * @param array the array to process
     * @param delimiter to split each element using (typically the equals symbol)
     * @return a {@code Properties} instance representing the array contents,
     * or {@code null} if the array to process was {@code null} or empty
     */

    public static Properties splitArrayElementsIntoProperties(String[] array, String delimiter) {
        return splitArrayElementsIntoProperties(array, delimiter, null);
    }

    /**
     * Take an array of strings and split each element based on the given delimiter.
     * A {@code Properties} instance is then generated, with the left of the
     * delimiter providing the key, and the right of the delimiter providing the value.
     * <p>Will trim both the key and value before adding them to the
     * {@code Properties} instance.
     * @param array the array to process
     * @param delimiter to split each element using (typically the equals symbol)
     * @param charsToDelete one or more characters to remove from each element
     * prior to attempting the split operation (typically the quotation mark
     * symbol), or {@code null} if no removal should occur
     * @return a {@code Properties} instance representing the array contents,
     * or {@code null} if the array to process was {@code null} or empty
     */

    public static Properties splitArrayElementsIntoProperties(
            String[] array, String delimiter,  String charsToDelete) {

        if (array == null || array.length < 1) {
            return null;
        }

        Properties result = new Properties();
        for (String element : array) {
            if (charsToDelete != null) {
                element = deleteAny(element, charsToDelete);
            }
            String[] splittedElement = split(element, delimiter);
            if (splittedElement == null) {
                continue;
            }
            result.setProperty(splittedElement[0].trim(), splittedElement[1].trim());
        }
        return result;
    }

    /**
     * Tokenize the given {@code String} into a {@code String} array via a
     * {@link StringTokenizer}.
     * <p>Trims tokens and omits empty tokens.
     * <p>The given {@code delimiters} string can consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using {@link #delimitedListToStringArray}.
     * @param str the {@code String} to tokenize (potentially {@code null} or empty)
     * @param delimiters the delimiter characters, assembled as a {@code String}
     * (each of the characters is individually considered as a delimiter)
     * @return an array of the tokens
     * @see StringTokenizer
     * @see String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray( String str, String delimiters) {
        return tokenizeToStringArray(str, delimiters, true, true);
    }

    /**
     * Tokenize the given {@code String} into a {@code String} array via a
     * {@link StringTokenizer}.
     * <p>The given {@code delimiters} string can consist of any number of
     * delimiter characters. Each of those characters can be used to separate
     * tokens. A delimiter is always a single character; for multi-character
     * delimiters, consider using {@link #delimitedListToStringArray}.
     * @param str the {@code String} to tokenize (potentially {@code null} or empty)
     * @param delimiters the delimiter characters, assembled as a {@code String}
     * (each of the characters is individually considered as a delimiter)
     * @param trimTokens trim the tokens via {@link String#trim()}
     * @param ignoreEmptyTokens omit empty tokens from the result array
     * (only applies to tokens that are empty after trimming; StringTokenizer
     * will not consider subsequent delimiters as token in the first place).
     * @return an array of the tokens
     * @see StringTokenizer
     * @see String#trim()
     * @see #delimitedListToStringArray
     */
    public static String[] tokenizeToStringArray(
             String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

        if (str == null) {
            return EMPTY_STRING_ARRAY;
        }

        StringTokenizer st = new StringTokenizer(str, delimiters);
        List<String> tokens = new ArrayList<>();
        while (st.hasMoreTokens()) {
            String token = st.nextToken();
            if (trimTokens) {
                token = token.trim();
            }
            if (!ignoreEmptyTokens || token.length() > 0) {
                tokens.add(token);
            }
        }
        return toStringArray(tokens);
    }

    /**
     * Take a {@code String} that is a delimited list and convert it into a
     * {@code String} array.
     * <p>A single {@code delimiter} may consist of more than one character,
     * but it will still be considered as a single delimiter string, rather
     * than as a bunch of potential delimiter characters, in contrast to
     * {@link #tokenizeToStringArray}.
     * @param str the input {@code String} (potentially {@code null} or empty)
     * @param delimiter the delimiter between elements (this is a single delimiter,
     * rather than a bunch individual delimiter characters)
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray( String str,  String delimiter) {
        return delimitedListToStringArray(str, delimiter, null);
    }

    /**
     * Take a {@code String} that is a delimited list and convert it into
     * a {@code String} array.
     * <p>A single {@code delimiter} may consist of more than one character,
     * but it will still be considered as a single delimiter string, rather
     * than as a bunch of potential delimiter characters, in contrast to
     * {@link #tokenizeToStringArray}.
     * @param str the input {@code String} (potentially {@code null} or empty)
     * @param delimiter the delimiter between elements (this is a single delimiter,
     * rather than a bunch individual delimiter characters)
     * @param charsToDelete a set of characters to delete; useful for deleting unwanted
     * line breaks: e.g. "\r\n\f" will delete all new lines and line feeds in a {@code String}
     * @return an array of the tokens in the list
     * @see #tokenizeToStringArray
     */
    public static String[] delimitedListToStringArray(
             String str,  String delimiter,  String charsToDelete) {

        if (str == null) {
            return EMPTY_STRING_ARRAY;
        }
        if (delimiter == null) {
            return new String[] {str};
        }

        List<String> result = new ArrayList<>();
        if (delimiter.isEmpty()) {
            for (int i = 0; i < str.length(); i++) {
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        }
        else {
            int pos = 0;
            int delPos;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return toStringArray(result);
    }


}