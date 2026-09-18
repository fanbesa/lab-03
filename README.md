# CMPUT 301 : Lab 3 Participation Exercise

## Student Details

- **Full Name:** `Fenet Anbesa`
- **CCID:** `fanbesa`

## References and Resources
Stack Overflow, 
Android Developers

ChatGPT - General Prompt (other than the example provided in the code)
Prompt:
  Given this set of code conventions, are there any errors or inconsistencies in the conventions in my code? If there are any issues, where are they and which rule do they violate? 
\nResponse: 
  What I would actually change:
  If you're trying to make this code conform closely to the provided guide, I'd make these changes:
  Change 1
  .padding(all=8.dp)
  
  to:
  
  .padding(all = 8.dp)
  
  Change 2
  if (showEditCityFields) {
      editMessage = true
  }
  
  else {
  
  to:
  
  if (showEditCityFields) {
      editMessage = true
  } else {
  
  Change 3
  var showEditCityFields by remember { mutableStateOf(value = false) }
  
  to:
  
  var showEditCityFields by remember { mutableStateOf(false) }


List any resources used here, or simply put `N/A` if not applicable.

## Verbal Collaboration

| Student Name | CCID      |
| ------------ | --------- |
| `student`    | `student` |
| `<Add more>` | `<CCID>`  |
