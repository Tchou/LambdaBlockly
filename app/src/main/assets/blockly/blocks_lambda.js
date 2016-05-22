
Blockly.Blocks['lambda'] = {
  init: function() {
    this.appendStatementInput("BODY")
        .setCheck(null)
        .appendField("λ")
        .appendField(new Blockly.FieldTextInput("x"), "PARAM")
        .appendField("•");
    this.setPreviousStatement(true, null);
    this.setColour(120);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


Blockly.Blocks['var'] = {
  init: function() {
    this.appendDummyInput()
        .appendField(new Blockly.FieldTextInput("x"), "NAME");
    this.setPreviousStatement(true, null);
    this.setColour(330);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


Blockly.Blocks['apply'] = {
  init: function() {
    this.appendStatementInput("FUN")
        .setCheck(null);
    this.appendDummyInput();
    this.appendStatementInput("ARG")
        .setCheck(null);
    this.setPreviousStatement(true, null);
    this.setColour(225);
    this.setTooltip('');
    this.setHelpUrl('http://www.example.com/');
  }
};


//Term Builders

Blockly.JavaScript['var'] = function(block) {
  var text_name = block.getFieldValue('NAME');
  var code = 'JavaTermBuilder.pushVar("' + text_name + '");\n';
  return code;
};

Blockly.JavaScript['lambda'] = function(block) {
  var text_param = block.getFieldValue('PARAM');
  var statements_body = Blockly.JavaScript.statementToCode(block, 'BODY');
  var code = statements_body +
            'JavaTermBuilder.pushLambda("' + text_param + '");\n';
  return code;
};



Blockly.JavaScript['apply'] = function(block) {
  var statements_fun = Blockly.JavaScript.statementToCode(block, 'FUN');
  var statements_arg = Blockly.JavaScript.statementToCode(block, 'ARG');
  var code = statements_fun +
             statements_arg +
             'JavaTermBuilder.pushApply();\n';
  return code;
};


function evalBlock () {
   var code = Blockly.JavaScript.workspaceToCode(Blockly.mainWorkspace);
   JavaTermBuilder.reset();
   console.log(code);
   eval(code);
   JavaTermBuilder.eval();
};
