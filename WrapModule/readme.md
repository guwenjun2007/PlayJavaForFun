This file can be further combined with webcrawler to extract important concept

The module will format each post as follows:

Entities
should be wrapped in "strong" tags

Links
should be wrapped in "a href" tags that point to the corresponding links

Twitter
usernames should be wrapped in "a href" tags that point to "http://twitter.com/username" and are
displayed as the username

Input Example:

Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile
positions 14 through 22 → Entity
positions 0 through 5 → Entity
positions 48 through 56 → Twitter username
positions 37 through 47 → Link

Output Example:

<strong>Obama</strong> visited <strong>Facebook</strong> headquarters: <a
href=”http://bit.ly/xyz”>http://bit.ly/xyz</a> @<a href=” http://twitter.com/elversatile ”>elversatile</a>
