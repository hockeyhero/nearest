# Making Changes

## The Basics



### Fork

To work on the project you need to first fork the project. A fork is your own personal copy of the project. You create a fork by pressing the 'fork' button (see image below) on the *hockeyhero/nearest* project page. 

![](https://github.com/DavidEwan/nearest/blob/master/docs/img/fork.jpg)

GitHub will ask you where you want to put the fork; it should default to your personal GitHub account. 

Switching over to your local account, you should now have a repository called 'nearest'. From here select the Code button and copy to your clipboard the string. 

In the image below you can see I've switched to my personal GitHub account, I'm on the project page for my personal copy of the 'nearest' repository (it even says its 'forked' from hockeyhero), and I'm going to select the 'Code' button the expose the access string. 

![](C:\Work\nearest\docs\img\clone.png)

Finally, on your laptop, its time to use Git. Open a cmd.exe and enter the command: 

`git clone <access string>`

The image below shows an example. 

![](C:\Work\nearest\docs\img\clone2.png)

Git will pull down from GitHub the repository and now you have a local copy to work with. If you now `cd` into the nearest folder you can start executing git commands. 

*There are simpler ways to do this using desktop tools. You can probably do this all in VSCode and/or GitHub Desktop. I tend to stick to command line commands.* 

#### Git Remotes

Git has this concept of remotes and this is what they mean: 

- origin - this is your personal 'nearest' repository in GitHub
- upstream - this is the original hockeyhero/nearest repository in GitHub

This will become important later on when its time to merge.  For now you can execute the command:

`git remote`

and see that `origin` is defined but not `upstream`. 

### Pull Request

Now that you have your own GitHub repository and your own local copy you are free to do what you want to the code. 

If you make a change that you think should be merged back into the upstream hockeyhero/nearest you open a *Pull Request*. Its really a *merge request* because its a pull not a push; Its up to the maintainer (me) to decide if it should be merged and there are rules for all of that. 

### Issues



How to use GitHub, Git to make code changes. 

Fork - your own personal copy of the project

[My image](img/fork.png)

Pull Request Template

Step 1 is to fork the repository. A fork is your personal clone of the repository in your personal GitHub account. 

## Pull Requests

Successful software development is micro-management. 

you need an account (github.com)

Need to know Github and Git commands

Pull requests a process for change control. 

(go over basics of git) - know how to execute git commands from the cmd.exe

Pull Request - a request to submit change, a request to merge into master. There are steps you need to go through to get to this point. 

- checkout a branch
- create changes
- commit
- approve
- merge

### Checkout

Start point. You are working on your fork and its sync'd with master, you have a working repository. 

Create a new branch to work. 

- feature/
- bugfix/



Whether the branch is a feature branch or a bug fix branch is important because it will affect automatic versioning (semantic versioning).

### Commit

Commit your files to your own branch, to your own repo. 

### Create 

### Review

### Approval

### Merge

### Delete Branch

Why do all this? 

second pair of eyes

only increase the quality of the product. 

### Tools

github account

git installed. 

#### Github Desktop

Text Editor

### Permissions

|                                                         | Read | Triage | Write | Maintain | Admin |
| ------------------------------------------------------- | ---- | ------ | ----- | -------- | ----- |
| Close, reopen, assign pull request                      |      | x      | x     | x        | x     |
| Submit reviews on pull requests                         | x    | x      | x     | x        | x     |
| Request pull request reviews                            |      | x      | x     | x        | x     |
| Submit reviews that affect a pull requests mergeability |      |        | x     | x        | x     |

### Repository Settings

Collaborators - do I need this?

Branches - default is master. 

- add protection rule: all branches
  - require pull request to merge to master

All branches have this rule



Comments for Pull Requests - if we read it in 6 months time, do we know why this change is here? 

github.com/pulls

Viewing a Pull Request 

Files changed - 

- is this the min change required to meet the requirement. 
- Squash and merge
- 



Checkout Branch

git checkout bugfix/branch-name



`git clone https://github.com/DavidEwan/nearest.git`

Clone from origin (my personal GitHub account to my hard drive. This creates a subfolder called nearest that is a copy of the project, or more specifically, your fork copy of the project. This is your own personal sandbox. 

`cd /d nearest`

set the current directory  to be .\nearest. 

The command `git remote` tells git to list all known remotes. f you type `git remote` git responds with `origin`, which is the name git gives to your forked copy of the project. To make git aware of the original  repository you need to type: 

`git remote add upstream  https://github.com/hockeyhero/nearest.git`

Now when you type the command `git remote` git answers with 

`origin`

`upstream`

To resync your fork from the main project do the following: 

`git fetch upstream master`

git log HEAD..upstream/master

git pull upstream master

git push

Shortcut

git remote update

# Issues

Going back to https://github.com/hockeyhero/nearest

Check out the Issues list, 

## Issue Labels

good first issue - good for first-time contributors

help wanted - for more experienced developers

Signal intent to take on an issue - add a comment (Is this available to work on?) 

(formal ownership of issues appears to be missing in Github)

Use the comment section of the Issue to record all traffic. 



## Merge Conflict











