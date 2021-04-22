let mainFunction = () => {
  console.log("mainFunction ->")
  let varA = 20
  console.log("varA: " +varA)
  let function2 = () => {
    console.log("function2 ->")
    varB = 50
    varA = 600
    console.log("varA: " +varA + "\nvarB: " + varB)
    let function3 = () => {
      console.log("function3 ->")
      varB = 900
      varC = 10
      console.log("varA: " +varA + "\nvarB: " + varB + "\nvarC: " + varC)
    }
    function3();
  }
  function2();
}
mainFunction();
