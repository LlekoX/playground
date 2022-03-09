from gtts import gTTS
import os

def textTOspeech():
    file = open("readme.txt","r")
    fileText = file.read().replace("\n"," ")
    language = 'en'
    outputAudio = gTTS(text=fileText, lang=language, slow=False)
    outputAudio.save("outputAudio.mp3")
    file.close()
    os.system("start outputAudio.mp3")
    
textTOspeech()