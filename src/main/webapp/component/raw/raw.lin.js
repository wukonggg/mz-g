function objToString(obj) {
  var desc = "";
  for (var i in obj) {
    var property = obj[i];
    desc += i + " = " + property + "\n";
  }
  return desc;
}
