#include "Fraction.h"
#include <ostream>
#include <cassert>
using namespace std;

Fraction::Fraction(int n, int d)
{
	numer = n;
	assert(d != 0);
	denom = d;
}

void Fraction::setDenom(int d) 
{
	assert(d != 0);
	denom = d; 
}

double Fraction::fractionToDecimal() const
{
	double dec;
	dec = static_cast<double>(numer) / denom;
	return dec;
}

ostream& operator<<(ostream& cout, const Fraction& right)
{
	cout << right.getNumer() << "/" << right.getDenom();
	return cout;
}

istream& operator>>(istream& cin, Fraction& right)
{
	int n, d;
	
	cout << "-- Enter numerator: ";
	cin >> n;
	right.setNumer(n);
	
	cout << "-- Enter denominator: ";
	cin >> d;
	right.setDenom(d);
	
	cout << endl;
	return cin;
}

bool operator==(const Fraction& left, const Fraction& right)
{
	return left.fractionToDecimal() == right.fractionToDecimal();
}

bool operator!=(const Fraction& left, const Fraction& right)
{
	return !(left == right);
}

Fraction operator*(const Fraction& left, const Fraction& right)
{
	Fraction product;

	product.setNumer(left.getNumer() * right.getNumer());
	product.setDenom(left.getDenom() * right.getDenom());
	
	return product;
}

Fraction operator/(const Fraction& left, const Fraction& right)
{
	Fraction quotient;

	quotient.setNumer(left.getNumer() * right.getDenom());
	quotient.setDenom(left.getDenom() * right.getNumer());

	return quotient;
}