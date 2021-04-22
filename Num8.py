def mainFunction():
  print("mainFunction ->")
  varA = 20
  print("varA: " + str(varA))
  def function2():
    print("function2 ->")
    global varA
    varB = 50
    varA = 600
    print("varA: " +str(varA) + "\nvarB: " + str(varB))
    def function3():
      print("function3 ->")
      global varB
      varB = 900
      varC = 10
      print("varA: " +str(varA) + "\nvarB: " + str(varB) + "\nvarC: " + str(varC))   
    function3()
  function2()
mainFunction()
