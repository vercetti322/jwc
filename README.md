<h1 align="center">
  jwc
</h1>
<div align="center">
  <img src="https://skillicons.dev/icons?i=java,maven,windows" />
</div>

<br/>

<p>
  jwc is a windows utility of <code>wc</code> on Unix. It runs through a batch file referenced by the <code>jar</code>. 
  You can find the maven profile at <a href="https://github.com/vercetti322/jwc/blob/master/pom.xml">pom.xml</a>. It uses
  <code>picocli</code> dependency for command creation, check it out at <a href="https://picocli.info/#_getting_started">picocli docs</a>
  This is a rather simple challenge from <a href="https://codingchallenges.fyi/challenges/intro/">coding challenges</a>. Check out
  my other repositories to find solutions to other challenges.
</p>

<h2 align="center">
  How to use?
</h2>
<p>
  Just hit <code>jwc --help</code> to get all the commands!
</p>

```powershell
❯ jwc --help
Usage: jwc [-bcfhlLVw] [-awL] [-wf] <filepath>
wc functionality of Unix on Windows
      <filepath>          path to the file
      -awL, --average-word-length
                          average word length
  -b, --bytes             byte count in file
  -c, --chars             character count in file
  -f, --full              displays complete analysis
  -h, --help              Show this help message and exit.
  -l, --lines             line count in file
  -L, --max-line-length   longest line in file
  -V, --version           Print version information and exit.
  -w, --words             word count in file
      -wf, --word-frequency
                          top words wrt count
```
<h2 align="center">
  Installation
</h2>
<p>
  Simply download the zip file <a href="https://github.com/vercetti322/jwc/raw/master/jwc.zip">download</a>, and extract the
  folder. Now add the folder to your system/user path. Now u can open any terminal and execute the commands just like on linux!
  You do require a jdk of version 8 or above for the cli tool to work. Happy coding!
</p>
