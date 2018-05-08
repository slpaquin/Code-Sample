#include <iostream>
using namespace std;

class Fraction
{
private:
	int numer;
	int denom;
public:
	Fraction(int n = 1, int d = 1);
	int getNumer() const { return numer; }
	int getDenom() const { return denom; }
	void setNumer(int n) { numer = n; }
	void setDenom(int d);
	double fractionToDecimal() const;
	
};

ostream& operator<<(ostream& cout, const Fraction& right);
istream& operator>>(istream& cin, Fraction& right);
bool operator==(const Fraction& left, const Fraction& right);
bool operator!=(const Fraction& left, const Fraction& right);
Fraction operator*(const Fraction& left, const Fraction& right);
Fraction operator/(const Fraction& left, const Fraction& right);