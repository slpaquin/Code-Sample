#include "Fraction.h"
#include <iostream>

using namespace std;

int main()
{
	Fraction one, two, three(3, 4), four(4);

	// Testing operator>>
	cout << "Enter fraction two " << endl;
	cin >> two;

	// Testing operator<<
	cout << "Fraction one  : " << one << endl;
	cout << "Fraction two  : " << two << endl;
	cout << "Fraction three: " << three << endl << endl;

	cout << endl;
	cout << "Fractions two * three = " << two * three << endl;
	cout << "Fractions two / three = " << two / three << endl;

	cout << endl;
	return 0;
}