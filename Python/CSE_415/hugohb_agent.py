# Shrink4.py
# A conversational "doctor" simulation modelled loosely
# after J. Weizenbaum's ELIZA.
# This Python program goes with the book "The Elements of Artificial
# Intelligence".
# This version of the program runs under Python 3.x.

# Steven Tanimoto
# (C) 2012.
#Hugo Baldner

from re import *   # Loads the regular expression module.
from random import choice

def Shrink():
    'Shrink is the top-level function, containing the main loop.'


def introduce():
    out = 'Evening gentlemen my name is Dr. King Schultz, and I\'m a bounty hunter.'
    out += '\nI was programmed by Hugo Baldner.'
    out += '\nHe can be contacted at hugohb@uw.edu'
    out += '\nSo... you got a job for me?'
    return out


def agentName():
    return "Dr. King Schultz"


def respond(theInput):
    wordlist = split(' ', remove_punctuation(theInput))
    # undo any initial capitalization:
    wordlist[0] = wordlist[0].lower()
    mapped_wordlist = you_me_map(wordlist)
    mapped_wordlist[0] = mapped_wordlist[0].capitalize()

    backup = addmemory(wordlist)
    if wordlist[0]=='':
        return"Son my time is valuable, hurry your talking."
        
    if wordlist[0:2] == ['i','hate']:
        return "Tell me what " +\
              stringify(mapped_wordlist[2:]) + ' did to you.'
        
    if wpred(wordlist[0]):
        return "You tell me " + wordlist[0] + " it has to go down."

    if 'job' in wordlist:
        return "I\'ll do this job for you... for a price."

    if wordlist[0:2] == ['i','feel']:
        fel = ["I don\'t care about your feelings.", "And why should I care about that?"]
        return fel[counter(1)%2]

    if 'revolver' in wordlist:
        ret = ["Oh I know about revolvers","Revolvers are my specialty"]
        return ret[counter(0)%2]

    if 'alive' in wordlist:
        return "I don\'t do alive"

    if wordlist[0:2] == ['they','are']:
        return "Don\'t worry I can handle people who are " +\
              stringify(mapped_wordlist[2:]) + '.'

    if verbp(wordlist[0]):
        return "Why do you want me to " +\
              stringify(mapped_wordlist) + '?'

    if wordlist[0:3] == ['has','a','weapon']:
        return "Well how good is he with that weapon?"

    if wordlist[0:2]==['can','you'] or wordlist[0:2]==['could','you']:
        return "Perhaps I " + wordlist[0] + ' ' +\
             stringify(mapped_wordlist[2:]) + '. For a price that is.'

    if 'wife' in wordlist:
        return "I wish I was lucky enough to marry."

    if 'germany' in wordlist:
        return "Ahhh I\'m from Germany, what do you know about it?"

    if 'money' in wordlist:
        return "Money does make the world go round."

    if 'slaves' in wordlist:
        return "I hate slavery. It is a dreadful thing"

    y = counter(2)
    if y %2 == 0:
        return punt()
    else:
        return "I remember you talking about how " + backup + " earlier. Lets get back to that"





def addmemory(x):
    MEMORY = ["this job will be easy", "this job will be hard", "this job will pay well",
              "this job will be long", "this job will suck", "this job will require creativity",
              "this job will be personal"]
    MEMORY[counter(3) % 6] = stringify(x)
    return choice(MEMORY)

def stringify(wordlist):
    'Create a string from wordlist, but with spaces between words.'
    return ' '.join(wordlist)


punctuation_pattern = compile(r"\,|\.|\?|\!|\;|\:")


def remove_punctuation(text):
    'Returns a string without any punctuation.'
    return sub(punctuation_pattern,'', text)


def wpred(w):
    'Returns True if w is one of the question words.'
    return (w in ['when','why','where','how'])


def dpred(w):
    'Returns True if w is an auxiliary verb.'
    return (w in ['do','can','should','would'])


PUNTS = ["Let\'s get back on track here",
         'Tell me more about this job you might have for me.',
         'I see....',
         'What does that mean in laymen terms?',
         'But why should I be concerned about it?',
         'Just tell me about the job.']

punt_count = 0


def punt():
    'Returns one from a list of default responses.'
    global punt_count
    punt_count += 1
    return PUNTS[punt_count % 6]


CASE_MAP = {'i':'you', 'I':'you', 'me':'you','you':'me',
            'my':'your','your':'my',
            'yours':'mine','mine':'yours','am':'are'}


def you_me(w):
    'Changes a word from 1st to 2nd person or vice-versa.'
    try:
        result = CASE_MAP[w]
    except KeyError:
        result = w
    return result


def you_me_map(wordlist):
    'Applies YOU-ME to a whole sentence or phrase.'
    return [you_me(w) for w in wordlist]


def verbp(w):
    'Returns True if w is one of these known verbs.'
    return (w in ['go', 'have', 'be', 'try', 'eat', 'take', 'help',
                  'make', 'get', 'jump', 'write', 'type', 'fill',
                  'put', 'turn', 'compute', 'think', 'drink',
                  'blink', 'crash', 'crunch', 'add'])


def counter(x):
    count = [0,0,0,0]
    count[x] = count[x] +1
    return count[x]

Shrink() # Launch the program.