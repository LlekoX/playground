import speech_recognition as sr

def speechRec():
    r = sr.Recognizer()
    with sr.Microphone() as source:
        print("Speak: ")
        audio = r.listen(source)

        try:
            text = r.recognize_google(audio)
            file = open("outputText.txt","w")
            file.write(text)
            file.close()
        except:
            print("Sorry I could not understand that, could you please repeate.")

speechRec()