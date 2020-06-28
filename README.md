# CSE-11-Projects

Description For "StringSearch.java":
This code takes in a file and then performs the tasks dictated by the query and transformation given. 
There are several queries (ex: contains, length, greater, etc.). There are also several transformations (ex: upper, lower, etc.).

Here is an example:
The file being used is called poem.txt and contains the following - 
"This is a short file
It contains text and this is
Also a haiku"

Input: $ java StringSearch "poem.txt" "greater=3&less=100&not(ends='z')" "replace='i';'I'"
Output:
ThIs Is a short fIle
It contaIns text and thIs Is
Also a haIku
