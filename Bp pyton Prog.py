/*
We define a function remove_duplicates that takes an input list as its parameter.
Inside the function, we convert the input list to a set by using set(input_list). Sets in Python do not allow duplicate elements, so any duplicates will be automatically removed in this step.
After removing duplicates, we convert the set back to a list using list(unique_elements). This step is necessary because the function is expected to return a list with duplicate elements removed.
Finally, we return the new list without duplicates
*/

def remove_duplicates(input_list):
    # Convert the input list to a set to remove duplicates
    unique_elements = set(input_list)
    
    # Convert the set back to a list
    unique_list = list(unique_elements)
    
    return unique_list

# Sample input list with duplicate elements
input_list = [1, 2, 2, 3, 4, 4, 5, 6, 6, 7]

# Remove duplicates and get the result
result_list = remove_duplicates(input_list)

print(result_list)
/*
The reason behind using a set is that sets are implemented using a hash table, which provides fast access and ensures that each element is unique. By converting the list to a set, duplicate elements are automatically eliminated. Then, we convert the set back to a list to present the final result in the same format as the input list.The only thing is that te original order is not preserved
*/